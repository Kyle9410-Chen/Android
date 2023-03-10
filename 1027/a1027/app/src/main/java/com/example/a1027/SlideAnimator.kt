package com.example.a1027

import android.view.View
import android.view.animation.Animation
import android.view.animation.Transformation

class SlideAnimator(view : View, ToHieght: Int, toWidth : Int) : Animation() {

    var toHeight = ToHieght
    var toWidth = toWidth
    var view = view

    override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {

        if (view.height != toHeight){
            view.layoutParams.height = (toHeight * interpolatedTime).toInt()
            view.layoutParams.width = (toWidth * interpolatedTime).toInt()
            view.requestLayout()
        }

    }

    override fun initialize(width: Int, height: Int, parentWidth: Int, parentHeight: Int) {
        super.initialize(width, height, parentWidth, parentHeight)
    }

    override fun willChangeBounds(): Boolean {

        return true
    }

}