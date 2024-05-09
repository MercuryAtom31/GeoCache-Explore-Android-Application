package com.example.geocacheexploreandroidapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment

class MainActivity : AppCompatActivity(), AddCacheDialogFragment.AddCacheDialogListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    // This function will be called when the "OK" button of the dialog is clicked
    override fun onDialogPositiveClick(cache: Geocache) {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as? NavHostFragment
        val cacheListFragment = navHostFragment?.childFragmentManager?.fragments?.find { it is CacheListFragment } as? CacheListFragment
        if (cacheListFragment != null) {
            cacheListFragment.addCacheToList(cache)
            Toast.makeText(this, "Cache '${cache.name}' added!", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Error: Cache list fragment not found", Toast.LENGTH_LONG).show()
        }
    }


    // This function will be called when the "Cancel" button of the dialog is clicked
    override fun onDialogNegativeClick() {
        Toast.makeText(this, "Add cache cancelled.", Toast.LENGTH_SHORT).show()
    }
}
