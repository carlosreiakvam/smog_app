package com.example.dte_2603_prosjekt.domain.model

import com.example.dte_2603_prosjekt.datasources.local.entities.DatabaseAQI
import com.example.dte_2603_prosjekt.datasources.local.entities.DatabaseAQIDescription


data class AQIDescription(
    val name: String,
    val units: String,
    val nameNO: String,
    val nameEN: String,
    val aqis: List<AQI>
)

data class AQI(
    val from: Int,
    val to: Int,
    val aqiClass: Int,
    val wmsColors: List<String>,
    val textEN: String,
    val textNO: String,
    val shortDescriptionNO: String,
    val shortDescriptionEN: String,
    val descriptionNO: String,
    val descriptionEN: String,
    val color: String
)

fun List<AQI>.asDatabaseAQIs(): List<DatabaseAQI> {
    return map {
        DatabaseAQI(
            from = it.from,
            to = it.to,
            aqiClass = it.aqiClass,
            wmsColors = it.wmsColors,
            textEN = it.textEN,
            textNO = it.textNO,
            shortDescriptionNO = it.shortDescriptionNO,
            shortDescriptionEN = it.shortDescriptionEN,
            descriptionNO = it.descriptionNO,
            descriptionEN = it.descriptionEN,
            color = it.color
        )
    }
}


fun List<AQIDescription>.asDatabaseAQIDescriptions(): List<DatabaseAQIDescription> {
    return map {
        DatabaseAQIDescription(
            name = it.name,
            units = it.units,
            nameNO = it.nameNO,
            nameEN = it.nameEN,
            aqis = it.aqis.asDatabaseAQIs()
        )
    }
}