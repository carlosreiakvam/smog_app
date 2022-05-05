package com.example.dte_2603_prosjekt.network

import com.example.dte_2603_prosjekt.datasources.remote.models.NetworkStation
import com.example.dte_2603_prosjekt.datasources.remote.models.PositionalData
import retrofit2.http.GET
import retrofit2.http.Query


interface AirQualityApiService {
    @GET("stations")
    suspend fun getStations(): List<NetworkStation>

    @GET(".")
    suspend fun getPositionalData(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double
    ): PositionalData
}
