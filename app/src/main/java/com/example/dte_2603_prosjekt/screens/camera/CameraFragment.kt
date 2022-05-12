package com.example.dte_2603_prosjekt.screens.camera

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.location.LocationRequest
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.dte_2603_prosjekt.databinding.FragmentCameraBinding
import com.example.dte_2603_prosjekt.screens.map.MapsViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.CancellationToken
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


class CameraFragment : Fragment() {
    private val viewModel: MapsViewModel by activityViewModels()
    private lateinit var binding: FragmentCameraBinding
    val REQUEST_IMAGE_CAPTURE = 1
    lateinit var currentPhotoPath: String
    lateinit var currentPhotoName: String
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private val locationListener = LocationListener {
    }


    @SuppressLint("MissingPermission", "InlinedApi")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCameraBinding.inflate(inflater)
        setHasOptionsMenu(true)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        binding.captureImage.isEnabled = false

        checkPermissions()

        binding.captureImage.setOnClickListener {
            dispatchTakePictureIntent()
            getLocation()
        }

        return binding.root
    }

    private fun dispatchTakePictureIntent() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val photoFile: File? = try {
            createImageFile()
        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }

        if (photoFile != null) {
            val photoURI: Uri = FileProvider.getUriForFile(
                requireContext(),
                "com.example.dte_2603_prosjekt.fileprovider",
                photoFile
            )
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
            startActivityForResult(cameraIntent, REQUEST_IMAGE_CAPTURE)
        }
    }

    @SuppressLint("SimpleDateFormat")
    @Throws(IOException::class)
    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp: String = SimpleDateFormat(
            "yy-MM-dd-HH-mm-ss-SS",
            Locale.getDefault()
        ).format(System.currentTimeMillis())
        val storageDir: File? = requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "FNL_${timeStamp}_",
            ".jpg",
            storageDir /* directory */
        ).apply {
            // Save a file: path for use with ACTION_VIEW intents
            currentPhotoPath = absolutePath
            currentPhotoName = name
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            //sette siste bilde i imageview
        }
    }

    fun checkPermissions() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireContext() as Activity,
                arrayOf(Manifest.permission.CAMERA), 0
            )
        } else {
            binding.captureImage.isEnabled = true
        }
    }

    @SuppressLint("MissingPermission")
    fun getLastKnownLocation() {
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location ->
                if (location != null) {
                    println("Hei!")
                }

            }
    }

    @SuppressLint("MissingPermission")
    fun getLocation() {
        val locationManager =
            requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager
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
            println("longitude: " + location?.longitude)
            println("latitude: " + location?.latitude)
        }
    }
}

