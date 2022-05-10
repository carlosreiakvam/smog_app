package com.example.dte_2603_prosjekt.screens.map

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.*
import com.example.dte_2603_prosjekt.domain.model.Station
import com.example.dte_2603_prosjekt.repository.AirQualityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
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

    val stations: LiveData<List<Station>> = liveData {
        repository.getStations()
            .onStart { Timber.d("started") }
            .catch { exception -> Timber.d(exception.message) }
            .collect { stations -> emit(stations) }
    }
}