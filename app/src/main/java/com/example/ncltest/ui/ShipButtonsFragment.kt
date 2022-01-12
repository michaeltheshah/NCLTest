package com.example.ncltest.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.ncltest.R
import com.example.ncltest.databinding.FragmentShipButtonsBinding

class ShipButtonsFragment : Fragment() {
    companion object {
        private const val SKY = "SKY"
        private const val BLISS = "BLISS"
        private const val ESCAPE = "ESCAPE"
    }
    private var _binding: FragmentShipButtonsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentShipButtonsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.skyButton.setOnClickListener {
            val action = ShipButtonsFragmentDirections.openShipInfo(SKY)
            findNavController().navigate(action)
        }

        binding.blissButton.setOnClickListener {
            val action = ShipButtonsFragmentDirections.openShipInfo(BLISS)
            findNavController().navigate(action)
        }

        binding.escapeButton.setOnClickListener {
            val action = ShipButtonsFragmentDirections.openShipInfo(ESCAPE)
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}