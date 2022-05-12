package com.example.dte_2603_prosjekt.screens.camera

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.location.LocationListener
import android.location.LocationManager
import android.os.Environment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dte_2603_prosjekt.repository.AirQualityRepository
import com.google.android.gms.location.FusedLocationProviderClient
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor(
    private val application: Application
) : ViewModel() {

    private val _currentPhotoPath = MutableLiveData<String>()
    private val _currentPhotoName = MutableLiveData<String>()
    private val _longitude = MutableLiveData<Double>()
    private val _latitude = MutableLiveData<Double>()
    lateinit var fusedLocationClient: FusedLocationProviderClient
    private val locationListener = LocationListener {
    }

    val currentPhotoPath get() = _currentPhotoPath
    val currentPhotoName get() = _currentPhotoName
    val longitude get() = _longitude
    val latitude get() = _latitude

    init {

    }

    @SuppressLint("MissingPermission")
    fun getLocation() {
        val locationManager =
            application.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val gpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

        if (gpsEnabled) {
            // sjekke om ny posisjon
            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                1000,
                0.0f,
                locationListener
            )

            // sist kjente posisjon
            val location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            _longitude.value = location?.longitude
            _latitude.value = location?.latitude
        }
    }

    @SuppressLint("SimpleDateFormat")
    @Throws(IOException::class)
    fun createImageFile(): File {
        // Create an image file name
        val timeStamp: String = SimpleDateFormat(
            "yy-MM-dd-HH-mm-ss-SS",
            Locale.getDefault()
        ).format(System.currentTimeMillis())
        val storageDir: File? = application.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "FNL_${timeStamp}_",
            ".jpg",
            storageDir /* directory */
        ).apply {
            // Save a file: path for use with ACTION_VIEW intents
            _currentPhotoPath.value = absolutePath
            _currentPhotoName.value = name
        }
    }


}