package com.example.dte_2603_prosjekt.datasources.local

import androidx.room.TypeConverter
import com.example.dte_2603_prosjekt.datasources.local.entities.DatabaseAQI
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@ExperimentalSerializationApi
class Converters {
    @TypeConverter
    fun fromList(value : List<String>) = Json.encodeToString(value)

    @TypeConverter
    fun toList(value: String) = Json.decodeFromString<List<String>>(value)

    @TypeConverter
    fun dbAQISFromList(value: List<DatabaseAQI>) = Json.encodeToString(value)

    @TypeConverter
    fun dbAQISToList(value: String) = Json.decodeFromString<List<DatabaseAQI>>(value)
}