package com.example.geocacheexploreandroidapplication

import AddCacheDialogFragment
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CacheList.newInstance] factory method to
 * create an instance of this fragment.
 */
class CacheList : Fragment(), AddCacheDialogFragment.AddCacheDialogListener{
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cache_list, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CacheList.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CacheList().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Assuming your "Add Geocache" button has the ID button_add_cache
        val addButton = view.findViewById<Button>(R.id.button_add_cache)
        addButton.setOnClickListener {
            showAddCacheDialog()
        }
    }

    // Function to show the Add Cache Dialog
    private fun showAddCacheDialog() {
        val dialog = AddCacheDialogFragment()
        // Set the target fragment for use later when sending results
        dialog.setTargetFragment(this, 0)
        dialog.show(parentFragmentManager, "AddCacheDialogFragment")
    }

    // Dialog positive click listener
    override fun onDialogPositiveClick(cache: Geocache) {
        // Here you handle the user input from the dialog.
        // You would typically save the cache to your database or data source here.
        // Then refresh the cache list to include the new cache.
    }

    // Dialog negative click listener
    override fun onDialogNegativeClick() {
        // Handle the case where the user cancels the dialog
        // Generally, you don't need to do anything here except dismiss the dialog
    }
}