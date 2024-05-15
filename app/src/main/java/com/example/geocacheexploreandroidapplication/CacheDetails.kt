package com.example.geocacheexploreandroidapplication

import android.location.Geocoder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.geocacheexploreandroidapplication.CacheDetailsArgs
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.io.IOException

class CacheDetails : Fragment(), OnMapReadyCallback {
    private val args: CacheDetailsArgs by navArgs()
    private lateinit var mMap: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cache_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val textView = view.findViewById<TextView>(R.id.textView)
        textView.text = """
            Name: ${args.geocache.name}
            Description: ${args.geocache.description}
            Address: ${args.geocache.address}
        """.trimIndent()

        view.findViewById<Button>(R.id.button_edit_cache).setOnClickListener {
            val action = CacheDetailsDirections.actionCacheDetailsToCacheEdit(args.geocache)
            findNavController().navigate(action)
        }

        val backButton = view.findViewById<Button>(R.id.button_back_to_list)
        backButton.setOnClickListener {
            findNavController().navigate(R.id.action_cacheDetails_to_cacheListFragment)
        }

        view.findViewById<Button>(R.id.button_view_on_map).setOnClickListener {
            // Fetch all geocaches from the database to pass to the MapFragment
            val databaseHelper = DatabaseHelper(requireContext())
            val geocaches = databaseHelper.getAllGeocaches().toTypedArray()
            val action = CacheDetailsDirections.actionCacheDetailsToMapFragment(geocaches)
            findNavController().navigate(action)
        }

        val mapFragment = childFragmentManager.findFragmentById(R.id.mapView) as SupportMapFragment
        mapFragment.getMapAsync(this)

        mapFragment.view?.setOnClickListener {
            // Fetch all geocaches from the database to pass to the MapFragment
            val databaseHelper = DatabaseHelper(requireContext())
            val geocaches = databaseHelper.getAllGeocaches().toTypedArray()
            val action = CacheDetailsDirections.actionCacheDetailsToMapFragment(geocaches)
            findNavController().navigate(action)
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val geocache = args.geocache
        val address = geocache.address
        val geocoder = Geocoder(requireContext())

        try {
            val addresses = geocoder.getFromLocationName(address, 1)
            if (addresses != null && addresses.isNotEmpty()) {
                val location = addresses[0]
                val latLng = LatLng(location.latitude, location.longitude)
                mMap.addMarker(MarkerOptions().position(latLng).title(geocache.name))
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
            } else {
                // ***
            }
        } catch (e: IOException) {
            e.printStackTrace()
            // ***
        }
    }
}