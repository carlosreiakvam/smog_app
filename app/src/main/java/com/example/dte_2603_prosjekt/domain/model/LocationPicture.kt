package com.example.dte_2603_prosjekt.domain.model

import com.example.dte_2603_prosjekt.datasources.local.entities.DatabaseLocationPicture

data class LocationPicture(val filename: String, val longitude: Double, val latitude: Double) {

}


fun List<LocationPicture>.asDatabaseLocationPicture(): List<DatabaseLocationPicture> {
    return map {
        DatabaseLocationPicture(
            filename = it.filename,
            longitude = it.longitude,
            latitude = it.latitude,
        )
    }
}