package com.example.dte_2603_prosjekt.screens.logged_in

import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.dte_2603_prosjekt.databinding.FragmentMainScreenBinding
import com.example.dte_2603_prosjekt.screens.AirQualityViewModel
import com.google.firebase.auth.FirebaseAuth


class LoggedInFragment : Fragment() {
    private lateinit var binding: FragmentMainScreenBinding
    private lateinit var mAuth: FirebaseAuth

    private val viewModel: AirQualityViewModel by lazy {
        ViewModelProvider(this)[AirQualityViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainScreenBinding.inflate(inflater)
        binding.airQualityViewModel = viewModel
        binding.lifecycleOwner = this

        mAuth = FirebaseAuth.getInstance()
        checkUserIsLoggedIn()

        binding.logoutButton.setOnClickListener {
            mAuth.signOut()
            checkUserIsLoggedIn()
        }

        return binding.root

    }

    private fun checkUserIsLoggedIn() {
        val firebaseUser = mAuth.currentUser
        if (firebaseUser != null) {
            binding.showEmailTextView.text = firebaseUser.email

        } else {
            this.findNavController()
                .navigate(LoggedInFragmentDirections.actionMainScreenFragmentToLoginFragment())
        }
    }


}