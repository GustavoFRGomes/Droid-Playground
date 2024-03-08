package cloud.ggomes.playground.fragments

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import cloud.ggomes.playground.MainActivity
import cloud.ggomes.playground.R
import cloud.ggomes.playground.result.ActivityForResult

class FirstFragment: Fragment() {
    lateinit var button: Button

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity() as? MainActivity)?.let {
            it.resultLauncher =
                registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                    if (result.resultCode == Activity.RESULT_OK) {
                        // There are no request codes
                        val data: Intent? = result.data

                        it.result = "From First Fragment with a RESULT!!!!"
                        // Handle the returned data here
                        it.supportFragmentManager.beginTransaction()
                            .replace(R.id.container, SecondFragment())
                            .addToBackStack("second")  // Add transaction to back stack
                            .commit()
                    }
                }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layout = inflater.inflate(R.layout.fragment_first, container, false)

        button = layout.findViewById(R.id.launch_for_result_button)

        return layout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button.setOnClickListener {
            (requireActivity() as? MainActivity)
                ?.resultLauncher
                ?.launch(
                    Intent(this@FirstFragment.context, ActivityForResult::class.java)
                )
        }
    }
}