package com.example.dte_2603_prosjekt.screens.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.dte_2603_prosjekt.databinding.FragmentRegisterBinding
import com.example.dte_2603_prosjekt.domain.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase


class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private lateinit var mAuth: FirebaseAuth
    private lateinit var fullName: String
    private lateinit var email: String
    private lateinit var password: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater)
        mAuth = FirebaseAuth.getInstance();


        binding.registerButton.setOnClickListener {
            fullName = binding.fullNameTextview.text.toString()
            email = binding.emailTextview.text.toString()
            password = binding.passwordTextview.text.toString()

            mAuth.createUserWithEmailAndPassword(
                email,
                password
            ).addOnCompleteListener {
                if (it.isSuccessful) {
                    FirebaseDatabase.getInstance().getReference("Users")
                        .child(FirebaseAuth.getInstance().currentUser!!.uid)
                        .setValue(User(fullName = fullName, age = 10, email = email))
                        .addOnCompleteListener { it1 ->
                            if (it1.isSuccessful) {
                                Toast.makeText(context, "Registrert", Toast.LENGTH_LONG).show()
                            } else {
                                Toast.makeText(context, "Noe gikk galt", Toast.LENGTH_LONG).show()
                            }
                        }
                }
            }
        }

        return binding.root

    }
}