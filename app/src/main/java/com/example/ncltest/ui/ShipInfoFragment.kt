package com.example.ncltest.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.ncltest.databinding.FragmentShipInfoBinding
import com.example.ncltest.util.state.State
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShipInfoFragment : Fragment() {
    val viewModel: ShipInfoViewModel by viewModels()
    val args: ShipInfoFragmentArgs by navArgs()
    private var _binding: FragmentShipInfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentShipInfoBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.shipLiveData.observe(viewLifecycleOwner) {
            when (it) {
                State.Loading -> {
                    binding.progressBar.isVisible = true
                }
                is State.Success -> {
                    binding.progressBar.isVisible = false
                    val value = it.value
                    binding.shipName.text = value.formattedShipName
                    binding.paxCapacity.text = value.formattedPaxCapacity
                    binding.crew.text = value.formattedCrew
                    binding.inauguralDate.text = value.formattedInauguralDate
                }
                is State.Error -> {
                    binding.progressBar.isVisible = false
                }
            }
        }

        viewModel.fetchShipInfo(args.shipName)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}