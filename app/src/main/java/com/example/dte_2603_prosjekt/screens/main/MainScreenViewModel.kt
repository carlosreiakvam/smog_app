package com.example.dte_2603_prosjekt.screens.auth

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dte_2603_prosjekt.repository.AirQualityRepository
import com.example.dte_2603_prosjekt.repository.AuthRepository
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val application: Application,
    private val airQualityRepository: AirQualityRepository,
    private val authRepository: AuthRepository
) : ViewModel() {


    private val _firebaseUser = MutableLiveData<FirebaseUser?>()
    private val _email = MutableLiveData<String>()

    val currentUser get() = _firebaseUser
    val email get() = _email

    init {
        _firebaseUser.value = authRepository.getCurrentUser()
    }

    fun logout() {
        authRepository.signOut()
        _firebaseUser.postValue(authRepository.getCurrentUser())
    }

    fun refreshStations() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                airQualityRepository.refreshStations().collect {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(application, "Success!", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(application, "Not Success!", Toast.LENGTH_SHORT).show()
                    Timber.d(e.message)
                }
            }
        }
    }

}