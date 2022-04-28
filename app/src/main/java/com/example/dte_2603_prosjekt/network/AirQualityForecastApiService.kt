package com.example.dte_2603_prosjekt.network

import com.example.dte_2603_prosjekt.domain.models.Station
import retrofit2.http.GET
import retrofit2.http.Query

interface AirQualityForecastApiService {
    @GET("stations")
    suspend fun getStations(): List<Station>

//    @GET(".")
//    suspend fun getPositionalData(
//        @Query("lat") lat: Double,
//        @Query("lon") lon: Double
//    ): PositionalData
}
