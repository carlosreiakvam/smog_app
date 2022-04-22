package com.example.dte_2603_prosjekt.screens.main_screen

import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.dte_2603_prosjekt.databinding.FragmentMainScreenBinding
import com.example.dte_2603_prosjekt.screens.login.LoginFragmentDirections
import com.google.firebase.auth.FirebaseAuth


class MainScreenFragment : Fragment() {
    private lateinit var binding: FragmentMainScreenBinding
    private lateinit var mAuth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainScreenBinding.inflate(inflater)
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

        } else {
            this.findNavController()
                .navigate(MainScreenFragmentDirections.actionMainScreenFragmentToLoginFragment())
        }
    }


}