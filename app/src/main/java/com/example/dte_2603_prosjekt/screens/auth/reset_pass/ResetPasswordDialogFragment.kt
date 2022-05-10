package com.example.dte_2603_prosjekt.screens.auth.reset_pass

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.dte_2603_prosjekt.R
import com.example.dte_2603_prosjekt.databinding.FragmentDialogResetPasswordBinding
import com.example.dte_2603_prosjekt.screens.auth.login.LoginViewModel

class ResetPasswordDialogFragment : DialogFragment() {

    private val viewModel: LoginViewModel by activityViewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val binding = FragmentDialogResetPasswordBinding.inflate(layoutInflater)
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setView(binding.root)
                .setPositiveButton(R.string.reset,
                    DialogInterface.OnClickListener { dialog, id ->
                        viewModel.resetPassword(binding.email.text.toString())
                    })
                .setNegativeButton(R.string.cancel,
                    DialogInterface.OnClickListener { dialog, id ->
                        // User cancelled the dialog
                    })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}