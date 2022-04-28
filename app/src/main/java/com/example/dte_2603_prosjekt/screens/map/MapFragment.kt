package com.example.dte_2603_prosjekt.screens.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.dte_2603_prosjekt.databinding.FragmentMapBinding
import com.example.dte_2603_prosjekt.screens.AirQualityViewModel


class MapFragment : Fragment() {
    lateinit var binding: FragmentMapBinding

    private val viewModel: AirQualityViewModel by lazy {
        ViewModelProvider(this)[AirQualityViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMapBinding.inflate(inflater)
        binding.airQualityViewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }
}