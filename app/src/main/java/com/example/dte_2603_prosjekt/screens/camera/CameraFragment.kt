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
    private val viewModel: CameraViewModel by activityViewModels()
    private lateinit var binding: FragmentCameraBinding
    val REQUEST_IMAGE_CAPTURE = 1


    @SuppressLint("MissingPermission", "InlinedApi")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCameraBinding.inflate(inflater)
        binding.lifecycleOwner = this
        setHasOptionsMenu(true)

        viewModel.fusedLocationClient =
            LocationServices.getFusedLocationProviderClient(requireContext())
        binding.captureImage.isEnabled = false

        checkPermissions()

        binding.captureImage.setOnClickListener {
            dispatchTakePictureIntent()
            viewModel.getLocation()
        }

        return binding.root
    }

    private fun dispatchTakePictureIntent() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val photoFile: File? = try {
            viewModel.createImageFile()
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
        viewModel.fusedLocationClient.lastLocation
            .addOnSuccessListener { location ->
                if (location != null) {
                    println("Hei!")
                }

            }
    }

}

