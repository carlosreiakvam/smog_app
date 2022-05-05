package com.example.dte_2603_prosjekt.datasources.remote.models

import android.os.Parcelable
import androidx.lifecycle.Transformations.map
import com.example.dte_2603_prosjekt.domain.model.Station
import com.example.dte_2603_prosjekt.domain.model.SubArea
import kotlinx.parcelize.Parcelize

@Parcelize
data class NetworkSubArea(
    val name: String,
    val areacode: String
) : Parcelable {

    fun asDomainSubArea(): SubArea {
        return SubArea(
            name = this.name,
            areacode = this.areacode
        )
    }
}

