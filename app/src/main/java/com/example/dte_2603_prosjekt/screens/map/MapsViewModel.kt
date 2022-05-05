package com.example.dte_2603_prosjekt.screens.map

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dte_2603_prosjekt.domain.model.Station
import com.example.dte_2603_prosjekt.repository.AirQualityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MapsViewModel @Inject constructor(
    private val application: Application,
    private val repository: AirQualityRepository
) : ViewModel() {


    private val _stations = MutableLiveData<List<Station>>()

    val stations: LiveData<List<Station>> get() = _stations

    init {
        getStations()
    }

    private fun getStations() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.getStations().collect {
                    withContext(Dispatchers.Main) {
                        _stations.value = it
                        Timber.d(it.size.toString())
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