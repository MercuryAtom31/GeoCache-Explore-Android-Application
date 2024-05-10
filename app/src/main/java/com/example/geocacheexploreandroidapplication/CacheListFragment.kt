//package com.example.geocacheexploreandroidapplication
//
//import AddCacheDialogFragment
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Toast
//import androidx.fragment.app.Fragment
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView

/**
 * CacheListFragment
 *
 * Purpose: Hosts the RecyclerView and acts as the container for our list.
 * It handles the lifecycle events for managing the RecyclerView and its adapter.
 */

package com.example.geocacheexploreandroidapplication

import AddCacheDialogFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.geocacheexploreandroidapplication.CacheListFragmentDirections


class CacheListFragment : Fragment(), AddCacheDialogFragment.AddCacheDialogListener {
    private lateinit var recyclerView: RecyclerView
    private val cacheList = mutableListOf<Geocache>()
    private lateinit var adapter: CacheListAdapter
    private lateinit var addButton: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_cache_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
//        adapter = CacheListAdapter(cacheList)
        adapter = CacheListAdapter(cacheList) { geocache ->
//            val action = CacheListFragmentDirections.actionCacheListFragmentToCacheDetailsFragment(geocache.id)
            val action = CacheListFragmentDirections.actionCacheListFragmentToCacheDetails()
            findNavController().navigate(action)
        }
        recyclerView.adapter = adapter

        addButton = view.findViewById<Button>(R.id.button_add_cache)
        addButton.setOnClickListener {
            showAddCacheDialog()
        }
        // Button to navigate back to the LandingPage
        view.findViewById<Button>(R.id.button_back_to_landing).setOnClickListener {
            findNavController().navigate(R.id.action_cacheListFragment_to_landingPage)
        }
    }

    private fun showAddCacheDialog() {
        val dialog = AddCacheDialogFragment()
        dialog.setTargetFragment(this, 0)
        dialog.show(parentFragmentManager, "AddCacheDialogFragment")
    }

    override fun onDialogPositiveClick(cache: Geocache) {
        addCacheToList(cache)
        Toast.makeText(context, "Cache '${cache.name}' added!", Toast.LENGTH_LONG).show()
    }

    override fun onDialogNegativeClick() {
        Toast.makeText(context, "Add cache cancelled.", Toast.LENGTH_SHORT).show()
    }

    fun addCacheToList(cache: Geocache) {
        cacheList.add(cache)
        adapter.notifyItemInserted(cacheList.size - 1)
    }
}


//class CacheListFragment : Fragment() {
//    private lateinit var recyclerView: RecyclerView
//    private val cacheList = mutableListOf<Geocache>()
//    private lateinit var adapter: CacheListAdapter
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_cache_list, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        recyclerView = view.findViewById(R.id.recyclerView)
//        recyclerView.layoutManager = LinearLayoutManager(context)
//        adapter = CacheListAdapter(cacheList)
//        recyclerView.adapter = adapter
//    }
//
//    // Method to update the list and refresh the RecyclerView
//    fun addCacheToList(cache: Geocache) {
//        cacheList.add(cache)  // Update your data source
//        adapter.notifyItemInserted(cacheList.size - 1)  // Notify the adapter of item inserted
//    }
//}





