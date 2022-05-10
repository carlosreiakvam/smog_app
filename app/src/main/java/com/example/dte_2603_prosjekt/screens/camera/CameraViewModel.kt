package com.example.dte_2603_prosjekt.screens.camera

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.dte_2603_prosjekt.repository.AirQualityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor(
    private val application: Application
) : ViewModel() {

    init {

    }

}