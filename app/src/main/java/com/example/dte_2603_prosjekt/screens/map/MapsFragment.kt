package com.example.dte_2603_prosjekt.screens.map

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.*
import androidx.navigation.fragment.findNavController
import com.example.dte_2603_prosjekt.R
import com.example.dte_2603_prosjekt.databinding.FragmentMapsBinding

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsFragment : Fragment() {

    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        val startLocation = LatLng(60.164834, 10.264241)
        val zoom = 10f
        googleMap.addMarker(MarkerOptions().position(startLocation).title("Start Location!"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(startLocation, zoom))
    }

    private lateinit var binding: FragmentMapsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMapsBinding.inflate(inflater)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.maps_toolbar_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_item_save -> {
                this.findNavController().navigate(
                    MapsFragmentDirections.actionMapsFragmentToSaveFragment()
                )
                return true
            }
            R.id.menu_item_details -> {
                this.findNavController().navigate(
                    MapsFragmentDirections.actionMapsFragmentToDetailsFragment()
                )
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}