package com.example.geocacheexploreandroidapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout

import androidx.navigation.fragment.findNavController


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LandingPage.newInstance] factory method to
 * create an instance of this fragment.
 */
class LandingPage : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_landing_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Find the FrameLayout containers
        val frameCacheList = view.findViewById<FrameLayout>(R.id.frame_cache_list)
        val frameMapView = view.findViewById<FrameLayout>(R.id.frame_map_view)

        // Set click listeners on the FrameLayout containers
        frameCacheList.setOnClickListener {
            // Navigate to CacheListFragment
            findNavController().navigate(R.id.action_landingPage_to_cacheListFragment)
        }
        frameMapView.setOnClickListener {
            // Navigate to MapFragment
            findNavController().navigate(R.id.action_landingPage_to_mapFragment)
        }
    }
}