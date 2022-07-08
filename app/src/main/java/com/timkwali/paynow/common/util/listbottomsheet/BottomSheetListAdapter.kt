package com.timkwali.paynow.common.util.listbottomsheet

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.timkwali.paynow.databinding.ListRvItemBinding

class BottomSheetListAdapter(
    private val bottomSheetList: List<String>,
    private val listener: OnItemClickListener
): RecyclerView.Adapter<BottomSheetListAdapter.ListViewHolder>() {

    inner class ListViewHolder(
        private val binding: ListRvItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(listItem: String) {
            binding.listItemTv.text = listItem
            binding.root.setOnClickListener {
                listener.onItemClick(listItem, adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ListRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val currentItem = bottomSheetList[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return bottomSheetList.size
    }
}

interface  OnItemClickListener {
    fun onItemClick(item: String, position: Int)
}