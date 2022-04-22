package com.example.dte_2603_prosjekt.screens.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.dte_2603_prosjekt.databinding.FragmentRegisterBinding
import com.example.dte_2603_prosjekt.screens.login.LoginFragmentDirections
import com.google.firebase.auth.FirebaseAuth


class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private lateinit var mAuth: FirebaseAuth
    private lateinit var email: String
    private lateinit var password: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater)
        mAuth = FirebaseAuth.getInstance();


        binding.registerButton.setOnClickListener {
            email = binding.emailTextview.text.toString()
            password = binding.passwordTextview.text.toString()

            mAuth.createUserWithEmailAndPassword(
                email,
                password
            ).addOnSuccessListener {
                val firebaseUser = mAuth.currentUser
                val email = firebaseUser!!.email

                Toast.makeText(context, "Konto med e-post $email opprettet", Toast.LENGTH_SHORT)
                    .show()
                findNavController().popBackStack()

            }.addOnFailureListener {
                Toast.makeText(context, "Noe gikk galt", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        return binding.root

    }
}