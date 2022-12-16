package com.example.timewarpscan.ui

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Shader
import android.util.AttributeSet
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.timewarpscan.R


class GradientTextView : androidx.appcompat.widget.AppCompatTextView {
    val strokeWidth = 12f
    private var isDrawing = false
    constructor(context: Context?) : super(context!!, null, -1)
    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs, -1)
    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(context!!,
        attrs,
        defStyle)


    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        Log.i("TAG", "onDraw: ")
        if (strokeWidth > 0) {

            paint.shader = null
            setTextColor(ContextCompat.getColor(context, R.color.brown))
            paint.strokeWidth = strokeWidth
            paint.style = Paint.Style.STROKE
            super.onDraw(canvas)

            isDrawing = true
            paint.shader = LinearGradient(
                0f,
                0f,
                0f,
                height.toFloat(),
                intArrayOf(
                    ContextCompat.getColor(context, R.color.gradient_text_first_color),
                    ContextCompat.getColor(context, R.color.gradient_text_second_color),
                    ContextCompat.getColor(context, R.color.gradient_text_third_color)
                ),
                floatArrayOf(
                    0.2f, 0.6f, 0.8f
                ),
                Shader.TileMode.CLAMP
            )
            paint.strokeWidth = 0f
            paint.style = Paint.Style.FILL
            super.onDraw(canvas)
            isDrawing = false

        } else {
            super.onDraw(canvas)
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        Log.i("TAG", "onMeasure: ")
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun invalidate() {
        Log.i("TAG", "invalidate: ")
        if (isDrawing) return
        super.invalidate()
    }
}