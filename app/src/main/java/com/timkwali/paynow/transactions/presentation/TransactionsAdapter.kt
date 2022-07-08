package com.timkwali.paynow.transactions.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.timkwali.paynow.R
import com.timkwali.paynow.databinding.TransactionRvItemBinding
import com.timkwali.paynow.transactions.domain.model.Transaction

class TransactionsAdapter(val clickCallback: (transaction: Transaction) -> Unit):
    ListAdapter<Transaction, TransactionsAdapter.TransactionsViewHolder>(TransactionDiffCallback) {

    inner class TransactionsViewHolder(private val binding: TransactionRvItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(transaction: Transaction) {
            binding.apply {
                name.text = transaction.customerName
                amount.text = transaction.amount.toString()
                date.text = transaction.createdAt
            }
            binding.root.setOnClickListener { clickCallback(transaction) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionsViewHolder {
        val binding = TransactionRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TransactionsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TransactionsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

object TransactionDiffCallback: DiffUtil.ItemCallback<Transaction>() {
    override fun areItemsTheSame(oldItem: Transaction, newItem: Transaction): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Transaction, newItem: Transaction): Boolean {
        return oldItem.reference == newItem.reference
    }
}