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
    private val onClick: (Geocache) -> Unit  // Accept a lambda that receives a Geocache object
) : RecyclerView.Adapter<CacheListAdapter.ViewHolder>() {

    // Pass 'caches' along with 'onClick' to the ViewHolder
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.editTextCacheName)
        val descriptionTextView: TextView = view.findViewById(R.id.editTextCacheDescription)

        init {
            view.setOnClickListener {
                // Use the 'adapterPosition' to get the current item and trigger the click
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    val selectedCache = caches[adapterPosition]  // Access to 'caches' directly
                    onClick(selectedCache)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_cache_item, parent, false)
        return ViewHolder(view)  // No need to pass 'caches' or 'onClick' here, as it's accessible
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

