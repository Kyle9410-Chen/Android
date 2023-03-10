package com.example.a1006

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.annotation.NonNull
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.graphics.set
import kotlin.math.min

class CanvasView(context: Context, colorInt: Int,strokeWidth : Float,bitmap: Bitmap) : View(context) {

    var canvas : Canvas
    var bitmap : Bitmap
    var defaultBitmap : Bitmap
    var mode = 0
    var text = ""

    private var touchX = 0f
    private var touchY = 0f
    private var currentX = 0f
    private var currentY = 0f

    init {
        this.bitmap = Bitmap.createScaledBitmap(bitmap,720,900,false)
        defaultBitmap = this.bitmap.copy(this.bitmap.config,true)
        canvas = Canvas(this.bitmap)
    }

    val paint = Paint().apply {
        this.color = colorInt
        isAntiAlias = true
        isDither = true
        style = Paint.Style.STROKE
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
        this.strokeWidth = strokeWidth
        textSize = 40f
    }

    val text_paint = Paint().apply {
        this.color = colorInt
        textSize = 40f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(bitmap,0f,0f,null)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {

        touchX = event.x
        touchY = event.y

        when(mode){
            0 -> {
                when(event.action){
                    MotionEvent.ACTION_DOWN -> start()
                    MotionEvent.ACTION_MOVE -> move()
                }
            }
            1 -> {
                when(event.action){
                    MotionEvent.ACTION_DOWN -> start()
                    MotionEvent.ACTION_UP -> cir()
                }
            }
            2 -> {
                when(event.action){
                    MotionEvent.ACTION_DOWN -> start()
                    MotionEvent.ACTION_UP -> rect()
                }
            }
            3 -> {
                when(event.action){
                    MotionEvent.ACTION_DOWN -> start()
                    MotionEvent.ACTION_UP -> line()
                }
            }
            4 -> {
                when(event.action){
                    MotionEvent.ACTION_DOWN -> text()
                }
            }

        }
        return true
    }

    fun start(){
        currentX = touchX
        currentY = touchY
    }

    fun rect(){
        canvas.drawRect(currentX,currentY,touchX,touchY,paint)
        invalidate()
    }

    fun line(){
        canvas.drawLine(currentX,currentY,touchX,touchY,paint)
        invalidate()
    }

    fun text(){
        canvas.drawText(text,touchX,touchY,text_paint)
        invalidate()
    }

    fun cir(){
        canvas.drawCircle(currentX+(touchX-currentX)/2,currentY+(touchY-currentY)/2, min(Math.abs(currentY-touchY)/2,Math.abs(currentX-touchX)),paint)
        invalidate()
    }

    fun move(){
        val stopX = touchX
        val stopY = touchY
        canvas.drawLine(currentX,currentY,stopX,stopY,paint)
        currentX = touchX
        currentY = touchY

        invalidate()
    }
}