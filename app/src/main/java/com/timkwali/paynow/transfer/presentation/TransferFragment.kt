package com.timkwali.paynow.transfer.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.timkwali.paynow.R
import com.timkwali.paynow.common.data.api.model.request.Transfer
import com.timkwali.paynow.common.data.api.model.request.TransferRecipient
import com.timkwali.paynow.common.util.Constants
import com.timkwali.paynow.common.util.Resource
import com.timkwali.paynow.common.util.Utils
import com.timkwali.paynow.common.util.listbottomsheet.BottomSheetItemClickListener
import com.timkwali.paynow.common.util.listbottomsheet.ListBottomSheetFragment
import com.timkwali.paynow.databinding.FragmentTransferBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TransferFragment : Fragment() {

    private var _binding: FragmentTransferBinding? = null
    private val binding get() = _binding!!
    private val transferViewModel: TransferViewModel by viewModels()
    private lateinit var listBottomSheet: ListBottomSheetFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTransferBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            lifecycleScope.launchWhenStarted {
                transferViewModel.bankList.collect {
                    bankPb.isVisible = it is Resource.Loading
                    bankTil.error = it?.message
                }
            }

            lifecycleScope.launchWhenStarted {
                transferViewModel.accountNumberVerification.collect {
                    accountNumberPb.isVisible = it is Resource.Loading
                    accountName.isVisible = it is Resource.Success
                    accountName.text = it?.data?.accountName
                    if(it is Resource.Error) {
                        accountTil.error = it.message
                    }
                    if(it is Resource.Success) {
                        transferViewModel.createTransferRecipient(TransferRecipient(
                            accountTiet.text.toString(), bankTiet.text?.split("|")?.get(1),
                            Constants.NIGERIA_CURRENCY, accountName.text.toString(), Constants.NIGERIA_MONEY_TYPE
                        ))
                    }
                }
            }

            lifecycleScope.launchWhenStarted {
                transferViewModel.transferRecipient.collect {
                    continueBtn.isEnabled = it is Resource.Success
                    when(it) {
                        is Resource.Loading -> continueBtn.text = "Validating..."
                        is Resource.Success -> continueBtn.text = "Continue"
                        else -> {
//                            Toast.makeText(requireContext(), it?.message, Toast.LENGTH_SHORT).show()
                            continueBtn.text = "Continue"
                        }
                    }
                }
            }

            lifecycleScope.launchWhenStarted {
                transferViewModel.transfer.collect {
                    progress.isVisible = it is Resource.Loading
                    when(it) {
                        is Resource.Success -> {
                            Utils.showDialog(requireActivity(), "Transfer has been queued", it.message.toString()
                            ) { findNavController().popBackStack() }
                        }
                        is Resource.Error -> {
                            Utils.showDialog(requireActivity(), getString(R.string.app_name), it.message.toString())
                        }
                    }
                }
            }

            amountTiet.addTextChangedListener {
                if(isAmountValid(binding.amountTiet.text.toString().toLong())) {
                    amountTil.isErrorEnabled = false
                } else {
                    amountTil.error = "Please enter an amount between 100 and 10,000,000"
                }
            }

            accountTiet.addTextChangedListener {
                if(it?.length!! < 10) {
                    accountTil.error = "Account number must be 10 digits"
                    accountName.text = ""
                    continueBtn.isEnabled = false
                } else {
                    accountTil.isErrorEnabled = false
                    bankTiet.text?.split("|")?.get(1)
                        ?.let { it1 -> transferViewModel.verifyAccountNumber(it.toString(), it1) }
                }
            }

            bankTiet.setOnClickListener {
                val banksList = transferViewModel.getBanksListString()
                listBottomSheet = ListBottomSheetFragment(getString(R.string.banks), banksList)
                listBottomSheet.setBottomSheetListener(banksBottomSheetListener)
                listBottomSheet.show(childFragmentManager, listBottomSheet.tag)
            }

            continueBtn.setOnClickListener {
                if(isAmountValid(binding.amountTiet.text.toString().toLong())) {
                    top.isEnabled = false
                    val trf = Transfer(
                        amountTiet.text.toString(), narrationTiet.text.toString(),
                        transferViewModel.transferRecipient.value?.data?.recipientCode,
                        Constants.TRANSFER_SOURCE
                    )
                    Log.d("dsfkjaf", trf.toString())
                    transferViewModel.transferFunds(trf)
                } else Toast.makeText(requireContext(), "Please input a valid amount to continue.", Toast.LENGTH_LONG).show()
            }

            transfer.setOnClickListener { findNavController().popBackStack() }
        }
    }

    private val banksBottomSheetListener = object : BottomSheetItemClickListener {
        override fun onBottomSheetItemClick(bottomSheetItem: String) {
            binding.bankTiet.setText(bottomSheetItem)
            binding.accountTiet.isEnabled = true
            listBottomSheet.dismiss()
        }
    }

    private fun isAmountValid(amount: Long): Boolean {
        return amount >= Constants.LOWER_AMOUNT_LIMIT &&
                amount <= Constants.UPPER_AMOUNT_LIMIT
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}