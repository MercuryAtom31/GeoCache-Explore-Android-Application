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
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_landing_page, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        val buttonMap = view.findViewById<Button>(R.id.button_map)
//        val buttonList = view.findViewById<Button>(R.id.button_list)
//
//        buttonMap.setOnClickListener {
//            // Navigate to MapFragment
//            findNavController().navigate(R.id.action_landingPage_to_mapFragment)
//        }
//
//        buttonList.setOnClickListener {
//            // Navigate to CacheListFragment
//            findNavController().navigate(R.id.action_landingPage_to_cacheList)
//        }
//    }


//    ************************************************************************

//    // TODO: Rename and change types of parameters
//    private var param1: String? = null
//    private var param2: String? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
//    }
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
//                              savedInstanceState: Bundle?): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_landing_page, container, false)
//    }
//
//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment LandingPage.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic fun newInstance(param1: String, param2: String) =
//                LandingPage().apply {
//                    arguments = Bundle().apply {
//                        putString(ARG_PARAM1, param1)
//                        putString(ARG_PARAM2, param2)
//                    }
//                }
//    }
}