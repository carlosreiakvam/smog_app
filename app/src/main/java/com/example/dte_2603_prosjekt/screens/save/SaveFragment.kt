package com.example.dte_2603_prosjekt.screens.save

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dte_2603_prosjekt.databinding.FragmentSaveBinding

class SaveFragment : Fragment() {

    private lateinit var binding: FragmentSaveBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSaveBinding.inflate(inflater)
        return binding.root
    }
}