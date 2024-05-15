package com.example.geocacheexploreandroidapplication

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

class CacheDetails : Fragment() {
    private val args: CacheDetailsArgs by navArgs()

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
            val action = CacheDetailsDirections.actionCacheDetailsToMapFragment(args.geocache)
            findNavController().navigate(action)
        }
    }
}