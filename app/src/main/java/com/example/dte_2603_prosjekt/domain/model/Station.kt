package com.example.dte_2603_prosjekt.domain.model

import com.example.dte_2603_prosjekt.datasources.local.entities.DatabaseStation
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem
import com.squareup.moshi.Json

data class Station(
    val name: String,
    val eoi: String,
    val height: Double,
    val latitude: Double,
    val longitude: Double,
    @Json(name = "delomrade")
    val subarea: SubArea,
    @Json(name = "grunnkrets")
    val groundArea: SubArea,
    @Json(name = "kommune")
    val municipality: SubArea
) : ClusterItem {
    override fun getPosition(): LatLng {
        return LatLng(latitude, longitude)
    }

    override fun getTitle(): String {
        return name
    }

    override fun getSnippet(): String {
        return municipality.name
    }
}

fun List<Station>.asDatabaseStations(): List<DatabaseStation> {
    return map {
        DatabaseStation(
            name = it.name,
            eoi = it.eoi,
            height = it.height,
            latitude = it.latitude,
            longitude = it.longitude,
            subarea = it.subarea.asDatabaseSubArea(),
            groundArea = it.groundArea.asDatabaseSubArea(),
            municipality = it.municipality.asDatabaseSubArea()
        )
    }
}