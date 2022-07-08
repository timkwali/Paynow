package com.timkwali.paynow.transactions.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.timkwali.paynow.common.util.Resource
import com.timkwali.paynow.transactions.domain.model.Transaction
import com.timkwali.paynow.transactions.domain.usecases.GetTransactions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val getTransactions: GetTransactions
): ViewModel() {

    init {
        getTransactions()
    }

    private var _transactionList: MutableStateFlow<Resource<List<Transaction>>?> = MutableStateFlow(Resource.Loading())
    val transactionList: StateFlow<Resource<List<Transaction>>?> get() = _transactionList

    fun getTransactions() = viewModelScope.launch {
        getTransactions.invoke().collect {
            _transactionList.value = it
        }
    }
}