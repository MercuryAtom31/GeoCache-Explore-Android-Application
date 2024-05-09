package com.example.geocacheexploreandroidapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CacheListFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private val cacheList = mutableListOf<Geocache>()
    private lateinit var adapter: CacheListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cache_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerView) // Make sure this ID matches your RecyclerView ID in XML
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = CacheListAdapter(cacheList)
        recyclerView.adapter = adapter
    }

    // Method to update the list and refresh the RecyclerView
    fun addCacheToList(cache: Geocache) {
        cacheList.add(cache)  // Update your data source
        adapter.notifyItemInserted(cacheList.size - 1)  // Notify the adapter of item inserted
    }
}




//package com.example.geocacheexploreandroidapplication
//
//import android.os.Bundle
//import android.view.View
//import androidx.fragment.app.Fragment
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//
//class CacheListFragment : Fragment() {
//    private val cacheList = mutableListOf<Geocache>()
//    private lateinit var adapter: CacheListAdapter
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        adapter = CacheListAdapter(cacheList)
////        view.findViewById<RecyclerView>(R.id.cache_list_recyclerview).apply {
////            layoutManager = LinearLayoutManager(context)
////            adapter = this@CacheListFragment.adapter
////        }
//        view.findViewById<RecyclerView>(R.id.recyclerView).apply {
//            layoutManager = LinearLayoutManager(context)
//            adapter = this@CacheListFragment.adapter
//        }
//    }
//
//    // Method to update the list and refresh the RecyclerView
//    fun addCacheToList(cache: Geocache) {
//        adapter.addCache(cache)
//    }
//}
