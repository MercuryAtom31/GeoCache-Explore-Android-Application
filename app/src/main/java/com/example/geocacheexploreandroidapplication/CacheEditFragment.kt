package com.example.geocacheexploreandroidapplication

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

class CacheEditFragment : Fragment() {
    private val args: CacheEditFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cache_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val nameEditText = view.findViewById<EditText>(R.id.editTextCacheName)
        val descriptionEditText = view.findViewById<EditText>(R.id.editTextCacheDescription)
        val addressEditText = view.findViewById<EditText>(R.id.editTextCacheAddress)
        val saveButton = view.findViewById<Button>(R.id.button_save_cache)
        val deleteButton = view.findViewById<Button>(R.id.button_delete_cache)
        val backButton = view.findViewById<Button>(R.id.button_back_to_details)

        val geocache = args.geocache
        nameEditText.setText(geocache.name)
        descriptionEditText.setText(geocache.description)
        addressEditText.setText(geocache.address)

        saveButton.setOnClickListener {
            val updatedGeocache = geocache.copy(
                name = nameEditText.text.toString(),
                description = descriptionEditText.text.toString(),
                address = addressEditText.text.toString()
            )

            // Save updated geocache to database
            val databaseHelper = DatabaseHelper(requireContext())
            databaseHelper.updateGeocache(updatedGeocache)

            // Navigate back to details screen
            val action = CacheEditFragmentDirections.actionCacheEditToCacheDetails(updatedGeocache)
            findNavController().navigate(action)
        }

        deleteButton.setOnClickListener {
            showDeleteConfirmationDialog(geocache.id)
        }

        backButton.setOnClickListener {
            val action = CacheEditFragmentDirections.actionCacheEditToCacheDetails(geocache)
            findNavController().navigate(action)
        }
    }

    private fun showDeleteConfirmationDialog(geocacheId: Int) {
        AlertDialog.Builder(requireContext())
            .setTitle("Delete Geocache")
            .setMessage("Are you sure you want to delete this geocache?")
            .setPositiveButton("Yes") { dialog, _ ->
                deleteGeocache(geocacheId)
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun deleteGeocache(geocacheId: Int) {
        val databaseHelper = DatabaseHelper(requireContext())
        databaseHelper.deleteGeocache(geocacheId)

        // Navigate back to cache list
        findNavController().popBackStack(R.id.cacheListFragment, false)
    }
}