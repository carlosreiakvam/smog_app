package com.example.dte_2603_prosjekt.datasources.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.dte_2603_prosjekt.domain.model.AQI
import com.example.dte_2603_prosjekt.domain.model.AQIDescription
import kotlinx.serialization.Serializable

@Entity(tableName = "aqi_description")
data class DatabaseAQIDescription(
    @PrimaryKey
    val name: String,
    val units: String,
    val nameNO: String,
    val nameEN: String,
    val aqis: List<DatabaseAQI>
)

@Serializable
data class DatabaseAQI(
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


fun List<DatabaseAQI>.asDomainAQIs(): List<AQI> {
    return map {
        AQI(
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


fun List<DatabaseAQIDescription>.asDomainAQIDescriptions(): List<AQIDescription> {
    return map {
        AQIDescription(
            name = it.name,
            units = it.units,
            nameNO = it.nameNO,
            nameEN = it.nameEN,
            aqis = it.aqis.asDomainAQIs()
        )
    }
}
