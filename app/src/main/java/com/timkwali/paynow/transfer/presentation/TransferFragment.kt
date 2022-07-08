package com.timkwali.paynow.transfer.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.timkwali.paynow.databinding.FragmentTransferBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TransferFragment : Fragment() {

    private var _binding: FragmentTransferBinding? = null
    private val binding get() = _binding!!
    private val transferViewModel: TransferViewModel by viewModels()

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

        transferViewModel
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}