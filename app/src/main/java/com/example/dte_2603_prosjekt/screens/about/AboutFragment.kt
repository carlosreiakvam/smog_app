package com.example.dte_2603_prosjekt.screens.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dte_2603_prosjekt.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {

    private lateinit var binding: FragmentAboutBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAboutBinding.inflate(inflater)
        return binding.root
    }
}