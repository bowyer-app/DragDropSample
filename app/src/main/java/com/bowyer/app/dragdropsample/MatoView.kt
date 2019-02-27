package com.bowyer.app.dragdropsample

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class MatoView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = android.R.attr.textViewStyle
) : View(context, attrs, defStyleAttr) {
    private var paint: Paint = Paint()

    private val strokeW1 = 2f
    private var dp: Float = 0f
    private var posX: Float = 0f
    private var posY: Float = 0f
    private var radius: Float = 0f

    fun getPosX(): Float {
        return posX
    }

    fun getPosY(): Float {
        return posY
    }

    fun getRadius(): Float {
        return radius
    }

    init {
        dp = resources.displayMetrics.density
    }

    override fun onDraw(canvas: Canvas) {

        val xc = (width / 2).toFloat() + 20 * dp
        val yc = (height / 2).toFloat()
        radius = resources.getDimension(R.dimen.yadokoro_mato_size) / 2
        posX = xc - 20 * dp
        posY = yc - 0.5f * dp


        paint.strokeWidth = strokeW1 * dp
        paint.isAntiAlias = true
        paint.style = Paint.Style.STROKE

        canvas.drawCircle(posX, posY, radius, paint)

    }
}