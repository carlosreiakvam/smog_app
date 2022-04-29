package com.example.dte_2603_prosjekt.screens.auth.login

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.dte_2603_prosjekt.R
import com.example.dte_2603_prosjekt.databinding.FragmentLoginBinding
import com.example.dte_2603_prosjekt.screens.auth.LoginViewModel
import com.example.dte_2603_prosjekt.screens.auth.reset_pass.ResetPasswordDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        viewModel.currentUser.observe(viewLifecycleOwner) {
            if (null != it) {
                this.findNavController()
                    .navigate(LoginFragmentDirections.actionLoginFragmentToMainScreenFragment())
            }
        }

        binding.registerTextview.setOnClickListener {
            this.findNavController()
                .navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
        }

        binding.forgotPasswordTextView.setOnClickListener {
            this.showResetDialog()
        }

        return binding.root

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

    fun showResetDialog() {
        val dialog = ResetPasswordDialogFragment()
        dialog.show(parentFragmentManager, "Reset password")
    }

    private fun removeBackButton() {
        if (activity is AppCompatActivity) {
            (activity as AppCompatActivity?)?.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }
    }
}