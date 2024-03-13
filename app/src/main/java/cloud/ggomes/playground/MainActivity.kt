package cloud.ggomes.playground

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val button = findViewById<BorderStateButton>(R.id.cool_button)
        val textView = findViewById<TextView>(R.id.textView)

        textView.setOnClickListener {
            button.changeState(
                when(button.getState()) {
                    ButtonState.FILLED -> ButtonState.OUTLINED
                    ButtonState.OUTLINED -> ButtonState.FILLED
                }
            )
        }
    }
}