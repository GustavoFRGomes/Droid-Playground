package cloud.ggomes.playground.result

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import cloud.ggomes.playground.R

class ActivityForResult: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_for_result)

        findViewById<Button>(R.id.button).setOnClickListener {
            setResult(Activity.RESULT_OK)
            finish()
        }
    }

}