package com.example.geocacheexploreandroidapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CacheListAdapter(private val caches: MutableList<Geocache>) : RecyclerView.Adapter<CacheListAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.editTextCacheName)
        val descriptionTextView: TextView = view.findViewById(R.id.editTextCacheDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //val view = LayoutInflater.from(parent.context).inflate(R.layout.cache_item, parent, false)
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_cache_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cache = caches[position]
        holder.nameTextView.text = cache.name
        holder.descriptionTextView.text = cache.description
    }

    override fun getItemCount() = caches.size

    fun addCache(cache: Geocache) {
        caches.add(cache)
        notifyItemInserted(caches.size - 1)
    }
}
