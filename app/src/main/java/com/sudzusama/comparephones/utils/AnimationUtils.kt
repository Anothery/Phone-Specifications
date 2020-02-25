package com.sudzusama.comparephones.utils

import android.os.Build
import android.view.View
import android.view.animation.AlphaAnimation

class AnimationUtils {
    companion object {
        fun setAppearAnimation(view: View, duration: Long) {
            val anim = AlphaAnimation(0.0f, 1.0f)
            anim.duration = duration
            view.startAnimation(anim)
        }

        fun setDisappearAnimation(view: View, duration: Long) {
            val anim = AlphaAnimation(1.0f, 0.0f)
            anim.duration = duration
            view.startAnimation(anim)
        }
    }
}