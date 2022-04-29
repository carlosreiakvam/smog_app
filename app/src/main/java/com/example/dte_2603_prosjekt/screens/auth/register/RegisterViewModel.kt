package com.example.dte_2603_prosjekt.screens.auth

import android.app.Application
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dte_2603_prosjekt.domain.model.Response
import com.example.dte_2603_prosjekt.repository.AuthRepository
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val application: Application,
    private val repository: AuthRepository
) : ViewModel() {


    private val _firebaseUser = MutableLiveData<FirebaseUser?>()
    private val _email = MutableLiveData<String>()
    private val _password = MutableLiveData<String>()

    val currentUser get() = _firebaseUser
    val email get() = _email
    val password get() = _password

    init {
        _firebaseUser.value = repository.getCurrentUser()
    }


    fun registerUser() {
        if (!checkInput()) {
            Timber.d("Checkinput failed")
            return
        } else {
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    repository.signUpWithEmailPassword(email.value!!, password.value!!).collect {
                        withContext(Dispatchers.Main) {
                            when (it) {
                                is Response.Success -> {
                                    _firebaseUser.postValue(repository.getCurrentUser())
                                    Toast.makeText(
                                        application,
                                        "Konto med e-post $email opprettet",
                                        Toast.LENGTH_SHORT
                                    )
                                        .show()
                                }
                                is Response.Error -> {
                                    Toast.makeText(
                                        application,
                                        "Noe gikk galt",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                                else -> {
                                    Toast.makeText(
                                        application,
                                        "Noe gikk galt",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }

                        }
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(application, e.message, Toast.LENGTH_SHORT).show()
                    }

                }
            }
        }
    }


    private fun checkInput(): Boolean {
        return if (!Patterns.EMAIL_ADDRESS.matcher(email.value ?: "").matches()) {
            Toast.makeText(application, "Feil format på epost", Toast.LENGTH_SHORT).show()
            false
        } else if (TextUtils.isEmpty(password.value)) {
            Toast.makeText(application, "Passord mangler", Toast.LENGTH_SHORT).show()
            false
        } else {
            true
        }
    }
}