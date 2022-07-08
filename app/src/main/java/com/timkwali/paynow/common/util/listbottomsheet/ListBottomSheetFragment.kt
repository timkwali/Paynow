package com.timkwali.paynow.common.util.listbottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.timkwali.paynow.databinding.FragmentListBottomSheetBinding


class ListBottomSheetFragment(
    private val listHeading: String,
    private val listItems: List<String>,
) : BottomSheetDialogFragment() {

    private lateinit var listBottomSheetBinding: FragmentListBottomSheetBinding
    private var bottomSheetItemClickListener: BottomSheetItemClickListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        listBottomSheetBinding = FragmentListBottomSheetBinding.inflate(layoutInflater, container, false)
        return listBottomSheetBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listBottomSheetBinding.apply {
            headingTv.text = listHeading
            val adapter = BottomSheetListAdapter(listItems, onItemClickListener)
            listsRv.adapter = adapter
            listsRv.layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private var onItemClickListener: OnItemClickListener = object : OnItemClickListener {
        override fun onItemClick(item: String, position: Int) {
            bottomSheetItemClickListener?.onBottomSheetItemClick(item)
        }
    }

    fun setBottomSheetListener(listener: BottomSheetItemClickListener) {
        bottomSheetItemClickListener = listener
    }
}