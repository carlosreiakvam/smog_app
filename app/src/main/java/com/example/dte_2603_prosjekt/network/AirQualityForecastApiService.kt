package com.example.dte_2603_prosjekt.network

import com.example.dte_2603_prosjekt.datasources.remote.models.NetworkAQIDescription
import com.example.dte_2603_prosjekt.datasources.remote.models.NetworkStation
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET


interface AirQualityApiService {
    @GET("stations")
    suspend fun getStations(): List<NetworkStation>

    @GET("aqi_description")
    suspend fun getAQIDescription(): NetworkAQIDescription
}
