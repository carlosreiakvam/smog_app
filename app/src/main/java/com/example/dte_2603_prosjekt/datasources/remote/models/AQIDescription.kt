package com.example.dte_2603_prosjekt.datasources.remote.models

import com.example.dte_2603_prosjekt.domain.model.AQI
import com.example.dte_2603_prosjekt.domain.model.AQIDescription
import com.squareup.moshi.Json

data class NetworkAQIDescription(
    val variables: Map<String, NetworkVariable>
) {
    fun asDomainObject(): List<AQIDescription> {
        return variables.map {
            AQIDescription(
                name = it.key,
                units = it.value.units ?: "",
                nameNO = it.value.nameNO,
                nameEN = it.value.nameEN,
                aqis = it.value.aqis.asDomainObject()
            )
        }
    }
}

data class NetworkVariable(
    val units: String?,
    @Json(name = "aqi_formula")
    val aqiFormula: String,
    val nameNO: String,
    val nameEN: String,
    val sources: List<NetworkSources>,
    val aqis: List<NetworkAQI>
)

data class NetworkSources(
    val units: String,
    val nameNO: String,
    val nameEN: String,
    val source: String
)

data class NetworkAQI(
    val from: Int?,
    val to: Int?,
    @Json(name = "class")
    val aqiClass: Int,
    @Json(name = "wms_colors")
    val wmsColors: List<String>?,
    @Json(name = "wms_minmax")
    val wmsMinMax: List<Int>?,
    val text: String,
    @Json(name = "text_NO")
    val textNO: String,
    @Json(name = "short_description_NO")
    val shortDescriptionNO: String,
    @Json(name = "short_description_EN")
    val shortDescriptionEN: String,
    @Json(name = "description_NO")
    val descriptionNO: String,
    @Json(name = "description_NN")
    val descriptionNN: String,
    @Json(name = "description_EN")
    val descriptionEN: String,
    val color: String
)


fun List<NetworkAQI>.asDomainObject(): List<AQI> {
    return map {
        AQI(
            from = it.from ?: it.wmsMinMax?.minOrNull() ?: 0,
            to = it.to ?: it.wmsMinMax?.maxOrNull() ?: 1,
            aqiClass = it.aqiClass,
            wmsColors = it.wmsColors ?: emptyList(),
            textEN = it.text,
            textNO = it.textNO,
            shortDescriptionNO = it.shortDescriptionNO,
            shortDescriptionEN = it.shortDescriptionEN,
            descriptionNO = it.descriptionNO,
            descriptionEN = it.descriptionNO,
            color = it.color
        )
    }
}
