package com.example.dte_2603_prosjekt.screens.auth

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dte_2603_prosjekt.repository.AuthRepository
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoggedInViewModel @Inject constructor(
    private val application: Application,
    private val repository: AuthRepository
) : ViewModel() {


    private val _firebaseUser = MutableLiveData<FirebaseUser?>()
    private val _email = MutableLiveData<String>()

    val currentUser get() = _firebaseUser
    val email get() = _email

    init {
        _firebaseUser.value = repository.getCurrentUser()
    }

    fun logout() {
        repository.signOut()
        _firebaseUser.postValue(repository.getCurrentUser())
    }

}