package com.example.dte_2603_prosjekt.screens.auth.logged_in

import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.dte_2603_prosjekt.databinding.FragmentMainScreenBinding
import com.example.dte_2603_prosjekt.screens.auth.AuthViewModel
import com.google.firebase.auth.FirebaseAuth


class LoggedInFragment : Fragment() {
    private lateinit var binding: FragmentMainScreenBinding
    private val viewModel: AuthViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainScreenBinding.inflate(inflater)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        viewModel.currentUser.observe(viewLifecycleOwner) {
            if (null == it) {
                findNavController()
                    .navigate(LoggedInFragmentDirections.actionMainScreenFragmentToLoginFragment())
            }
        }

        return binding.root
    }
}