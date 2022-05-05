package com.example.dte_2603_prosjekt.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dte_2603_prosjekt.datasources.remote.models.NetworkStation
import com.example.dte_2603_prosjekt.datasources.remote.models.PositionalData

class AirQualityViewModel : ViewModel() {

    private val _stations = MutableLiveData<List<NetworkStation>>()
    val stations: LiveData<List<NetworkStation>>
        get() = _stations

    private val _positionalData = MutableLiveData<PositionalData>()
    val positionalData: LiveData<PositionalData>
        get() = _positionalData

    init {
//        getPositionalData()
    }

//    // TODO: Bytt ut long med double i model for Station for at denne kan virke
//    private fun getAllStations() {
//        viewModelScope.launch {
//            try {
//                _stations.value = AirQualityApi.retrofitService.getStations()
//                Timber.d("Successfully ran getAllStations()")
//            } catch (e: Exception) {
//                Timber.d(e.message.toString())
//            }
//        }
//    }
//
//    //TODO: Hardcodede verdier for testing. Bytt senere
//    private fun getPositionalData() {
//        viewModelScope.launch {
//            try {
//                _positionalData.value = AirQualityApi.retrofitService
//                    .getPositionalData(60.0, 10.0)
//                Timber.d("Positional data: " + positionalData)
//                Timber.d("Successfully ran getPositionalData()")
//            } catch (e: Exception) {
//                Timber.d(e.message.toString())
//            }
//        }
//    }
}