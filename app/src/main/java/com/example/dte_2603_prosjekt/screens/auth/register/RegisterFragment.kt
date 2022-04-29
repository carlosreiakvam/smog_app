package com.example.dte_2603_prosjekt.screens.auth.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.dte_2603_prosjekt.databinding.FragmentRegisterBinding
import com.example.dte_2603_prosjekt.screens.auth.RegisterViewModel


class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private val viewModel: RegisterViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        viewModel.currentUser.observe(viewLifecycleOwner) {
            if (null != it) {
                findNavController().popBackStack()
            }
        }

        return binding.root

    }
}