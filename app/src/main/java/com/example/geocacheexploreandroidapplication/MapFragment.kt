package com.example.geocacheexploreandroidapplication

import android.location.Geocoder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import java.io.IOException

class MapFragment : Fragment(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap
    private val args: MapFragmentArgs by navArgs()
    private val geocacheMap = mutableMapOf<Marker, Geocache>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        view.findViewById<Button>(R.id.button_back_to_landing).setOnClickListener {
            findNavController().navigate(R.id.action_mapFragment_to_landingPage)
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val geocaches = args.geocaches
        val geocoder = Geocoder(requireContext())

        for (geocache in geocaches) {
            val address = geocache.address
            try {
                val addresses = geocoder.getFromLocationName(address, 1)
                if (addresses != null && addresses.isNotEmpty()) {
                    val location = addresses[0]
                    val latLng = LatLng(location.latitude, location.longitude)
                    val marker = mMap.addMarker(MarkerOptions().position(latLng).title(geocache.name))
                    marker?.let {
                        geocacheMap[it] = geocache
                        it.showInfoWindow()  // Ensure info window is shown
                    }
                    if (geocache == geocaches[0]) {
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10f))
                    }
                } else {
                    // ***
                }
            } catch (e: IOException) {
                e.printStackTrace()
                // ***
            }
        }

        mMap.setOnMarkerClickListener { marker ->
            val geocache = geocacheMap[marker]
            geocache?.let {
                val action = MapFragmentDirections.actionMapFragmentToCacheDetails(it)
                findNavController().navigate(action)
            }
            true
        }
    }
}