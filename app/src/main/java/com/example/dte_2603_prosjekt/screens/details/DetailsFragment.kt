package com.example.dte_2603_prosjekt.screens.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dte_2603_prosjekt.databinding.FragmentDetailBinding

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater)
        return binding.root
    }

}