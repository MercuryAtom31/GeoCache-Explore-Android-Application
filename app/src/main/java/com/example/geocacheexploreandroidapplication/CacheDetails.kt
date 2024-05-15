package com.example.geocacheexploreandroidapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
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
    }
}