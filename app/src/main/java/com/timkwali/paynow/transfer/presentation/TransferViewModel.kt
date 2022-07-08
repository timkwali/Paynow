package com.timkwali.paynow.transfer.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.timkwali.paynow.common.data.api.model.request.Transfer
import com.timkwali.paynow.common.data.api.model.request.TransferRecipient
import com.timkwali.paynow.common.data.api.model.response.Bank
import com.timkwali.paynow.common.data.api.model.response.TransferRecipientResponse
import com.timkwali.paynow.common.data.api.model.response.TransferResponse
import com.timkwali.paynow.common.data.api.model.response.VerifyAccountResponse
import com.timkwali.paynow.common.util.Resource
import com.timkwali.paynow.transfer.domain.usecases.CreateTransferRecipient
import com.timkwali.paynow.transfer.domain.usecases.GetBankList
import com.timkwali.paynow.transfer.domain.usecases.TransferFunds
import com.timkwali.paynow.transfer.domain.usecases.VerifyAccountNumber
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransferViewModel @Inject constructor(
    private val getBankList: GetBankList,
    private val verifyAccountNumber: VerifyAccountNumber,
    private val createTransferRecipient: CreateTransferRecipient,
    private val transferFunds: TransferFunds,
): ViewModel() {

    init {
//        val transferRecipient = TransferRecipient("3061580324", "011", "NGN", "John Doe", "nuban")
//        createTransferRecipient(transferRecipient)
//        verifyAccountNumber("3061580324", "011")
    }

    private var _bankList: MutableStateFlow<Resource<List<Bank>>?> = MutableStateFlow(null)
    val bankList: StateFlow<Resource<List<Bank>>?> get() = _bankList

    private var _accountNumberVerification: MutableStateFlow<Resource<VerifyAccountResponse>?> = MutableStateFlow(null)
    val accountNumberVerification: StateFlow<Resource<VerifyAccountResponse>?> get() = _accountNumberVerification

    private var _transferRecipient: MutableStateFlow<Resource<TransferRecipientResponse>?> = MutableStateFlow(null)
    val transferRecipient: StateFlow<Resource<TransferRecipientResponse>?> get() = _transferRecipient

    private var _transfer: MutableStateFlow<Resource<TransferResponse>?> = MutableStateFlow(null)
    val transfer: StateFlow<Resource<TransferResponse>?> get() = _transfer




    fun getBankList() = viewModelScope.launch {
        _bankList.value = Resource.Loading()
        getBankList.invoke().collect {
            _bankList.value = it
        }
    }

    fun verifyAccountNumber(accountNumber: String, bankCode: String) = viewModelScope.launch {
        _accountNumberVerification.value = Resource.Loading()
        verifyAccountNumber.invoke(accountNumber, bankCode).collect {
            _accountNumberVerification.value = it
        }
    }

    fun createTransferRecipient(transferRecipient: TransferRecipient) = viewModelScope.launch {
        _transferRecipient.value = Resource.Loading()
        createTransferRecipient.invoke(transferRecipient).collect {
            _transferRecipient.value = it
        }
    }

    fun transferFunds(transfer: Transfer) = viewModelScope.launch {
        _transfer.value = Resource.Loading()
        transferFunds.invoke(transfer).collect {
            _transfer.value = it
        }
    }
}