package com.example.dte_2603_prosjekt.domain.models

import android.os.Parcelable
import com.example.dte_2603_prosjekt.datasources.local.entities.DatabaseStation
import com.example.dte_2603_prosjekt.datasources.local.entities.Subarea
import com.example.dte_2603_prosjekt.datasources.local.entities.Groundarea
import com.example.dte_2603_prosjekt.datasources.local.entities.Municipality
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

//Groundarea = grunnkrets
//Subarea = delområde
//Municipality = kommune

@Parcelize
data class Station(
    val groundarea: @RawValue Groundarea,
    val height: Long,
    val eoi: String,
    val latitude: Long,
    val name: String,
    val subarea: @RawValue Subarea,
    val longitude: Long,
    val municipality: @RawValue Municipality

) : Parcelable


fun List<Station>.asDatabaseStations(): List<DatabaseStation> {
    return map {
        DatabaseStation(
            groundarea = it.groundarea,
            height = it.height,
            eoi = it.eoi,
            latitude = it.latitude,
            name = it.name,
            subarea = it.subarea,
            longitude = it.longitude,
            municipality = it.municipality,
        )
    }
}