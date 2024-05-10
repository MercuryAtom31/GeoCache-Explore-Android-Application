package com.example.geocacheexploreandroidapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * CacheListAdapter
 * Purpose: Manages the individual data items (Geocache objects)
 * and binds them to the views defined in the list item layout (R.layout.fragment_cache_item).
 * This class handles the display of data in the RecyclerView.
 */

class CacheListAdapter(
    private val caches: MutableList<Geocache>,
    private val onClick: (Int) -> Unit  // Accept a lambda that receives an Int (position)
) : RecyclerView.Adapter<CacheListAdapter.ViewHolder>() {

    class ViewHolder(view: View, val onClick: (Int) -> Unit) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.editTextCacheName)
        val descriptionTextView: TextView = view.findViewById(R.id.editTextCacheDescription)

        init {
            view.setOnClickListener {
                // Use the 'adapterPosition' to get the current item and trigger the click
                onClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //val view = LayoutInflater.from(parent.context).inflate(R.layout.cache_item, parent, false)
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_cache_item, parent, false)
        return ViewHolder(view, onClick)
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
