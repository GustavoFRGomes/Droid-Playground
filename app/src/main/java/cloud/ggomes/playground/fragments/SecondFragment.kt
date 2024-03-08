package cloud.ggomes.playground.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import cloud.ggomes.playground.MainActivity
import cloud.ggomes.playground.R

class SecondFragment: Fragment() {

    private lateinit var resultTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layout = inflater.inflate(R.layout.fragment_second, container, false)

        resultTextView = layout.findViewById<TextView>(R.id.result_from_activity_text_view)

        return layout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as? MainActivity)?.let {
            resultTextView.text = it.result
        }
    }

}