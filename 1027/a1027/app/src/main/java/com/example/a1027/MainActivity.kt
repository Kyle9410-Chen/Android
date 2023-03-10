package com.example.a1027

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Slide
import android.transition.TransitionManager
import android.view.Gravity
import android.view.View
import android.view.animation.AccelerateInterpolator
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import java.util.*
import kotlin.concurrent.timerTask

class MainActivity : AppCompatActivity() {

    var height = 0
    var inFlag = true
    var theme = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        height = fill_img.layoutParams.height

        b()
    }

    private fun b() {

        bright.setOnClickListener {

            if(!theme){

                if (bright.rotation == 0f || bright.rotation == 1080f){
                    back.setBackgroundColor(Color.parseColor("#ffffff"))
                    bright.setImageDrawable(resources.getDrawable(R.drawable.bright))
                    title_text.setTextColor(Color.parseColor("#000000"))
                    class_text.background.setTint(Color.parseColor("#000000"))
                    class_text.setTextColor(Color.parseColor("#000000"))
                    id_text.background.setTint(Color.parseColor("#000000"))
                    id_text.setTextColor(Color.parseColor("#000000"))
                    name_text.background.setTint(Color.parseColor("#000000"))
                    name_text.setTextColor(Color.parseColor("#000000"))
                    img.setImageDrawable(resources.getDrawable(R.drawable.square_dark))
                    fill_img.setImageDrawable(resources.getDrawable(R.drawable.fill_square_dark))
                    theme = true
                    bright.rotation = 0f
                    bright.animate().rotation(1080f).setDuration(400).start()
                }
            }

            else{

                if (bright.rotation == 0f || bright.rotation == 1080f){
                    back.setBackgroundColor(Color.parseColor("#2e2e2e"))
                    bright.setImageDrawable(resources.getDrawable(R.drawable.dark))
                    title_text.setTextColor(Color.parseColor("#ffffff"))
                    class_text.background.setTint(Color.parseColor("#ffffff"))
                    class_text.setTextColor(Color.parseColor("#ffffff"))
                    id_text.background.setTint(Color.parseColor("#ffffff"))
                    id_text.setTextColor(Color.parseColor("#ffffff"))
                    name_text.background.setTint(Color.parseColor("#ffffff"))
                    name_text.setTextColor(Color.parseColor("#ffffff"))
                    img.setImageDrawable(resources.getDrawable(R.drawable.square))
                    fill_img.setImageDrawable(resources.getDrawable(R.drawable.fill_square))
                    theme = false
                    bright.rotation = 0f
                    bright.animate().rotation(1080f).setDuration(400).start()
                }
            }

        }


        rotate.setOnClickListener {

            if (img.rotation == 0f || img.rotation == 360f){
                img.rotation = 0f
                fill_img.rotation = 0f
                img.animate().rotation(360f).setDuration(1500).start()
                fill_img.animate().rotation(360f).setDuration(1500).start()
            }
        }


        fly.setOnClickListener {

            if (fill_img.visibility == View.INVISIBLE){
                val slide = Slide()
                slide.duration = 500L
                slide.slideEdge = Gravity.LEFT
                TransitionManager.beginDelayedTransition(panel,slide)
                fill_img.visibility = View.VISIBLE
                inFlag = false

                Timer().schedule(object : TimerTask(){
                    override fun run() {
                        val slide = Slide()
                        slide.duration = 500L
                        slide.slideEdge = Gravity.RIGHT
                        TransitionManager.beginDelayedTransition(panel,slide)
                        fill_img.visibility = View.INVISIBLE
                    }
                },600)
            }


        }
    }
}