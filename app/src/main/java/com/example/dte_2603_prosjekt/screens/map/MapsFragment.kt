package com.example.dte_2603_prosjekt.screens.map

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import com.example.dte_2603_prosjekt.R
import com.example.dte_2603_prosjekt.databinding.FragmentMapsBinding
import com.example.dte_2603_prosjekt.domain.model.Station
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.collections.MarkerManager
import com.google.maps.android.ktx.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch
import timber.log.Timber

class MapsFragment : Fragment() {
    private val viewModel: MapsViewModel by activityViewModels()
    private lateinit var binding: FragmentMapsBinding
    private lateinit var clusterManager: ClusterManager<Station>
    private val startLocation = LatLng(59.911491, 10.757933)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMapsBinding.inflate(inflater)
        setHasOptionsMenu(true)
        val isRestore = savedInstanceState != null

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        lifecycle.coroutineScope.launchWhenCreated {
            val googleMap = mapFragment.awaitMap()
            if (!isRestore) {
                googleMap.awaitMapLoad()
                googleMap.animateCamera(
                    CameraUpdateFactory.newLatLngZoom(
                        startLocation,
                        10F
                    )
                )
            }
            showMapLayers(googleMap)
            setupMapBindings()
        }

        return binding.root
    }

    private fun showMapLayers(map: GoogleMap) {
        val markerManager = MarkerManager(map)

        addClusters(map, markerManager)
    }

    private fun setupMapBindings() {
        viewModel.stations.observe(viewLifecycleOwner) {
            clusterManager.addItems(it)
        }
    }

    private fun addClusters(map: GoogleMap, markerManager: MarkerManager) {
        clusterManager = ClusterManager<Station>(requireContext(), map, markerManager)
        map.setOnCameraIdleListener(clusterManager)
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

