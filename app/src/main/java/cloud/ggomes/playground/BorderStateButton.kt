package cloud.ggomes.playground

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton

class BorderStateButton: AppCompatButton {
    private var borderColor: Int = -1
    private var borderWidth: Float = -1f

    private var textColor: Int = -1
    private var backgroundColor: Int = -1

    private var currentState: ButtonState = ButtonState.FILLED
    private val outlinePaint = Paint()

    constructor(context: Context) : super(context) {
        initialiseView(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initialiseView(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initialiseView(context, attrs, defStyleAttr)
    }

    private fun initialiseView(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) {
        val typedArray = context.obtainStyledAttributes(
            attrs, R.styleable.BorderStateButton, defStyleAttr, 0)

        borderColor = typedArray.getColor(R.styleable.BorderStateButton_borderColor, -1)
        borderWidth = typedArray.getDimension(R.styleable.BorderStateButton_borderWidth, 0f)

        textColor = typedArray.getColor(R.styleable.BorderStateButton_android_textColor, -1)
        backgroundColor = typedArray.getColor(R.styleable.BorderStateButton_android_background, -1)

        require(!(backgroundColor == -1)) { "You must provide both textColor and/or backgroundColor!!" }

        currentState = ButtonState.fromValue(
            typedArray.getInt(R.styleable.BorderStateButton_initial_state, 0))

        // Set the outline color (same as text color)
        outlinePaint.color = if (borderColor == -1) textColor else borderColor
        outlinePaint.style = Paint.Style.STROKE
        outlinePaint.strokeWidth = borderWidth // Adjust the outline width as needed

        typedArray.recycle()
    }

    override fun onDraw(canvas: Canvas) {
        if (currentState == ButtonState.OUTLINED)
            canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), outlinePaint)

        super.onDraw(canvas)
    }

    fun changeState(state: ButtonState) {
        currentState = state

        if (state == ButtonState.FILLED) {
            setTextColor(textColor)
            setBackgroundColor(backgroundColor)
        } else {
            setTextColor(backgroundColor)
            setBackgroundColor(textColor)
        }

        invalidate()
    }

    fun getState(): ButtonState = currentState
}

private const val ANDROID_NAMESPACE = "http://schemas.android.com/apk/res/android"