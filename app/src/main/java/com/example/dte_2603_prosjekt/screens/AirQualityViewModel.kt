package com.example.dte_2603_prosjekt.screens

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dte_2603_prosjekt.domain.models.PositionalData
import com.example.dte_2603_prosjekt.domain.models.Station
import com.example.dte_2603_prosjekt.network.AirQualityApi
import kotlinx.coroutines.launch
import java.lang.Exception

class AirQualityViewModel : ViewModel() {

    private val _stations = MutableLiveData<List<Station>>()
    val stations: LiveData<List<Station>>
        get() = _stations

    private val _positionalData = MutableLiveData<PositionalData>()
    val positionalData: LiveData<PositionalData>
        get() = _positionalData

    init {
        getPositionalData()
    }

    private fun getAllStations() {
        viewModelScope.launch {
            try {
                _stations.value = AirQualityApi.retrofitService.getStations()
                Log.d("cre032", "Successfully ran getAllStations()")
            } catch (e: Exception) {
                Log.d("cre032", e.message.toString())
            }
        }
    }

    //TODO: Hardcodede verdier for testing inntil videre. Bytt ut senere
    private fun getPositionalData() {
        viewModelScope.launch {
            try {
                _positionalData.value = AirQualityApi.retrofitService
                    .getPositionalData(60.0, 10.0)
                Log.d("cre032", positionalData.toString())
                Log.d("cre032", "Successfully ran getPositionalData()")
            } catch (e: Exception) {
                Log.d("cre032", e.message.toString())
            }
        }
    }
}