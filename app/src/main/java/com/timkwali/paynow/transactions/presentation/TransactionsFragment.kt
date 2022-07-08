package com.timkwali.paynow.transactions.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.timkwali.paynow.R
import com.timkwali.paynow.common.util.Resource
import com.timkwali.paynow.common.util.Utils
import com.timkwali.paynow.common.util.listbottomsheet.BottomSheetListAdapter
import com.timkwali.paynow.databinding.FragmentTransactionsBinding
import com.timkwali.paynow.transactions.domain.model.Transaction
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TransactionsFragment : Fragment() {

    private var _binding: FragmentTransactionsBinding? = null
    private val binding get() = _binding!!
    private val transactionViewModel: TransactionViewModel by viewModels()
    private lateinit var rvAdapter: TransactionsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTransactionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvAdapter = TransactionsAdapter {
            Toast.makeText(requireContext(), "gotonext screent", Toast.LENGTH_SHORT).show() }

        binding.apply {
            lifecycleScope.launchWhenStarted {
                transactionViewModel.transactionList.collect {
                    progress.isVisible = it is Resource.Loading
                    message.isVisible = it is Resource.Error
                    retry.isVisible = it is Resource.Error
                    message.text = it?.message
                    if(it is Resource.Error) {
                        it.message?.let { it1 -> Utils.showDialog(requireActivity(), message = it1) }
                    }
                    it?.data?.let {
                        transactionsRv.isVisible = it.isNotEmpty()
                        setUpRecyclerView(it)
                    }
                }
            }

            retry.setOnClickListener { transactionViewModel.getTransactions() }
        }
    }

    private fun setUpRecyclerView(list: List<Transaction>) {
        binding.transactionsRv.apply {
            rvAdapter.submitList(list)
            adapter = rvAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}