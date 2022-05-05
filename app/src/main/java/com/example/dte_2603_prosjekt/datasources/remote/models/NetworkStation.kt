package com.example.dte_2603_prosjekt.datasources.remote.models

import android.os.Parcelable
import com.example.dte_2603_prosjekt.domain.model.Station
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class NetworkStation(
    val name: String,
    val eoi: String,
    val height: Double,
    val latitude: Double,
    val longitude: Double,
    @Json(name = "delomrade")
    val subarea: NetworkSubArea,
    @Json(name = "grunnkrets")
    val groundArea: NetworkSubArea,
    @Json(name = "kommune")
    val municipality: NetworkSubArea
) : Parcelable


fun List<NetworkStation>.asDomainStations(): List<Station> {
    return map {
        Station(
            groundArea = it.groundArea.asDomainSubArea(),
            height = it.height,
            eoi = it.eoi,
            latitude = it.latitude,
            name = it.name,
            subarea = it.subarea.asDomainSubArea(),
            longitude = it.longitude,
            municipality = it.municipality.asDomainSubArea(),
        )
    }
}


