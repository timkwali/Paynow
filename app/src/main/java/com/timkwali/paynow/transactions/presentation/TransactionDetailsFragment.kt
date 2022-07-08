package com.timkwali.paynow.transactions.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.timkwali.paynow.R
import com.timkwali.paynow.databinding.FragmentTransactionDetailsBinding
import com.timkwali.paynow.databinding.FragmentTransactionsBinding
import com.timkwali.paynow.transactions.domain.model.Transaction
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TransactionDetailsFragment : Fragment() {

    private var _binding: FragmentTransactionDetailsBinding? = null
    private val binding: FragmentTransactionDetailsBinding get() = _binding!!
    private val args : TransactionDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTransactionDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            name.text = args.transaction.customerName
            amount.text = args.transaction.amount.toString()
            status.text = args.transaction.status
            reference.text = args.transaction.reference
            date.text = args.transaction.createdAt

            transactionDetails.setOnClickListener { findNavController().popBackStack() }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
