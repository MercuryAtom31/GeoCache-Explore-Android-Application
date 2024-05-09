package com.example.geocacheexploreandroidapplication

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CacheListFragment : Fragment() {
    private val cacheList = mutableListOf<Geocache>()
    private lateinit var adapter: CacheListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = CacheListAdapter(cacheList)
//        view.findViewById<RecyclerView>(R.id.cache_list_recyclerview).apply {
//            layoutManager = LinearLayoutManager(context)
//            adapter = this@CacheListFragment.adapter
//        }
        view.findViewById<RecyclerView>(R.id.recyclerView).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@CacheListFragment.adapter
        }
    }

    // Method to update the list and refresh the RecyclerView
    fun addCacheToList(cache: Geocache) {
        adapter.addCache(cache)
    }
}
