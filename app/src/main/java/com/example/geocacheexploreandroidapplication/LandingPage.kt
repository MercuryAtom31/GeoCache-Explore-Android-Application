package com.example.geocacheexploreandroidapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import androidx.navigation.fragment.findNavController

class LandingPage : Fragment() {

    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_landing_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        databaseHelper = DatabaseHelper(requireContext())

        val frameCacheList = view.findViewById<FrameLayout>(R.id.frame_cache_list)
        val frameMapView = view.findViewById<FrameLayout>(R.id.frame_map_view)

        frameCacheList.setOnClickListener {
            findNavController().navigate(R.id.action_landingPage_to_cacheListFragment)
        }

        frameMapView.setOnClickListener {
            val geocaches = databaseHelper.getAllGeocaches().toTypedArray()
            val action = LandingPageDirections.actionLandingPageToMapFragment(geocaches)
            findNavController().navigate(action)
        }
    }
}