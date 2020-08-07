package com.example.kotlinapp.utils

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.OvershootInterpolator
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.kotlinapp.R

import com.google.android.material.snackbar.ContentViewCallback


class CustomSnackBarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr), ContentViewCallback {

    private val update: TextView
    private val stars: TextView
    private val forks: TextView

    init {
        View.inflate(context, R.layout.custom_snack, this)
        clipToPadding = false
        this.update = findViewById(R.id.update)
        this.forks = findViewById(R.id.forks)
        this.stars = findViewById(R.id.star)
    }

    override fun animateContentIn(delay: Int, duration: Int) {
        val scaleX = ObjectAnimator.ofFloat(update, View.SCALE_X, 0f, 1f)
        val scaleY = ObjectAnimator.ofFloat(update, View.SCALE_Y, 0f, 1f)
        val animatorSet = AnimatorSet().apply {
            interpolator = OvershootInterpolator()
            setDuration(500)
            playTogether(scaleX, scaleY)
        }
        animatorSet.start()
    }

    override fun animateContentOut(delay: Int, duration: Int) {
    }
}
