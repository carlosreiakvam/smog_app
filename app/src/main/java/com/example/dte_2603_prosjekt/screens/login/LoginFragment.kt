package com.example.dte_2603_prosjekt.screens.login

import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.dte_2603_prosjekt.R
import com.example.dte_2603_prosjekt.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth


class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var mAuth: FirebaseAuth
    private lateinit var email: String
    private lateinit var password: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater)

        mAuth = FirebaseAuth.getInstance();
        checkUserIsLoggedIn()


        binding.registerTextview.setOnClickListener {
            this.findNavController()
                .navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
        }

        binding.signInButton.setOnClickListener {
            email = binding.enterEmail.text.toString()
            password = binding.enterPassword.text.toString()

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(context, "Feil format på epost", Toast.LENGTH_SHORT).show()
            } else if (TextUtils.isEmpty(password)) {
                Toast.makeText(context, "Passord mangler", Toast.LENGTH_SHORT).show()
            } else {
                mAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
                    val firebaseUser = mAuth.currentUser
                    val email = firebaseUser!!.email
                    Toast.makeText(context, "Logget inn som $email", Toast.LENGTH_SHORT).show()

                    this.findNavController()
                        .navigate(LoginFragmentDirections.actionLoginFragmentToMainScreenFragment())


                }.addOnFailureListener {
                    Toast.makeText(context, "Login gikk galt", Toast.LENGTH_SHORT).show()
                }
            }


        }

        return binding.root

    }

    private fun checkUserIsLoggedIn() {
        val firebaseUser = mAuth.currentUser
        if (firebaseUser != null) {
            this.findNavController()
                .navigate(LoginFragmentDirections.actionLoginFragmentToMainScreenFragment())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu, menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                activity?.onBackPressed()
                return true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun removeBackButton() {
        if (activity is AppCompatActivity) {
            (activity as AppCompatActivity?)?.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }
    }
}