import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.example.geocacheexploreandroidapplication.Geocache
import com.example.geocacheexploreandroidapplication.R

/**
 *  This class is designed to show a dialog where a user can input details
 *  for a new Geocache, and then it communicates the result back to a listener.
 */

class AddCacheDialogFragment : DialogFragment() {

    interface AddCacheDialogListener {
        fun onDialogPositiveClick(cache: Geocache)
        fun onDialogNegativeClick()
    }

    // Use this instance of the interface to deliver action events
    lateinit var listener: AddCacheDialogListener

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            // Get the layout inflater
            val inflater = requireActivity().layoutInflater;
            val view = inflater.inflate(R.layout.add_cache_dialog, null)

            // Inflate and set the layout for the dialog
            // Pass null as the parent view because its going in the dialog layout
            builder.setView(view)
                // Add action buttons
                .setPositiveButton("OK") { dialog, id ->
                    val name = view.findViewById<EditText>(R.id.editTextCacheName).text.toString()
                    val description =
                        view.findViewById<EditText>(R.id.editTextCacheDescription).text.toString()
                    // Create a new Geocache and pass back to listener
                    listener.onDialogPositiveClick(Geocache(name, description))
                }
                .setNegativeButton("Cancel") { dialog, id ->
                    listener.onDialogNegativeClick()
                    getDialog()?.cancel()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
    // Override the Fragment.onAttach() method to instantiate the AddCacheDialogListener
    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Verify that the host fragment implements the callback interface
        try {
            // Instantiate the AddCacheDialogListener so we can send events to the host
            listener = context as AddCacheDialogListener
        } catch (e: ClassCastException) {
            // The activity doesn't implement the interface, throw an exception
            throw ClassCastException("$context must implement AddCacheDialogListener")
        }
    }
}