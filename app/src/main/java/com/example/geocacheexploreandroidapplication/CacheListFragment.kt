//package com.example.geocacheexploreandroidapplication
//
//import com.example.geocacheexploreandroidapplication.AddCacheDialogFragment
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Button
//import android.widget.Toast
//import androidx.fragment.app.Fragment
//import androidx.navigation.fragment.findNavController
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView

/**
 * CacheListFragment
 *
 * Purpose: Hosts the RecyclerView and acts as the container for our list.
 * It handles the lifecycle events for managing the RecyclerView and its adapter.
 */
package com.example.geocacheexploreandroidapplication

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

class CacheListFragment : Fragment(), AddCacheDialogFragment.AddCacheDialogListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CacheListAdapter
    private lateinit var addButton: Button
    lateinit var databaseHelper: DatabaseHelper

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_cache_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        databaseHelper = DatabaseHelper(requireContext())
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = CacheListAdapter(mutableListOf()) { geocache ->
            val action = CacheListFragmentDirections.actionCacheListFragmentToCacheDetails(geocache)
            findNavController().navigate(action)
        }
        recyclerView.adapter = adapter

        addButton = view.findViewById(R.id.button_add_cache)
        addButton.setOnClickListener {
            showAddCacheDialog()
        }
        view.findViewById<Button>(R.id.button_back_to_landing).setOnClickListener {
            findNavController().navigate(R.id.action_cacheListFragment_to_landingPage)
        }

        loadGeocaches()
    }
    private fun loadGeocaches() {
        val geocaches = databaseHelper.getAllGeocaches()  // Fetch geocaches from the database
        adapter.updateData(geocaches)  // Update the adapter with fetched data
    }
    private fun showAddCacheDialog() {
        val dialog = AddCacheDialogFragment()
        dialog.setTargetFragment(this, 0)
        dialog.show(parentFragmentManager, "AddCacheDialogFragment")
    }
    override fun onDialogPositiveClick(cache: Geocache) {
        databaseHelper.addGeocache(cache)  // Adds the cache to the database
        loadGeocaches()  // Reloads the cache list from the database and refreshes the RecyclerView
        Toast.makeText(context, "Cache '${cache.name}' added!", Toast.LENGTH_LONG).show()
    }
    override fun onDialogNegativeClick() {
        Toast.makeText(context, "Add cache cancelled.", Toast.LENGTH_SHORT).show()
    }
//    fun refreshData() {
//        loadGeocaches()
//    }
    fun addCacheToList(cache: Geocache) {
//        adapter.caches.add(cache)
//        adapter.notifyItemInserted(adapter.caches.size - 1)
        loadGeocaches()
    }
}