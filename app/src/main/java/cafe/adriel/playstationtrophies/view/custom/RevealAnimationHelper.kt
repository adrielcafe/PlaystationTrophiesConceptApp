package cafe.adriel.playstationtrophies.view.custom

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.graphics.Color
import android.support.v4.view.animation.FastOutSlowInInterpolator
import android.view.View
import android.view.ViewAnimationUtils
import io.mironov.smuggler.AutoParcelable

// Based on https://medium.com/@gabornovak/circular-reveal-animation-between-fragments-d8ed9011aec
object RevealAnimationHelper {
    private const val BG_COLOR = Color.WHITE
    private const val LONG_DURATION = 1000L
    private const val SHORT_DURATION = 500L

    fun startEnterAnimation(view: View, settings: AnimationSettings, listener: (() -> Unit)? = null) {
        view.addOnLayoutChangeListener(object : View.OnLayoutChangeListener {
            override fun onLayoutChange(v: View, left: Int, top: Int, right: Int, bottom: Int, oldLeft: Int, oldTop: Int, oldRight: Int, oldBottom: Int) {
                v.removeOnLayoutChangeListener(this)

                val finalRadius = Math.hypot(settings.width.toDouble(), settings.height.toDouble()).toFloat()
                val anim = ViewAnimationUtils.createCircularReveal(v, settings.centerX, settings.centerY, 0F, finalRadius)
                anim.run {
                    duration = LONG_DURATION
                    interpolator = FastOutSlowInInterpolator()
                    addListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            listener?.let { it() }
                        }
                    })
                    start()
                }
                startBackgroundAnimation(view, LONG_DURATION)
            }
        })
    }

    fun startExitAnimation(view: View, settings: AnimationSettings, listener: (() -> Unit)? = null) {
        val initRadius = Math.sqrt((settings.width * settings.width + settings.height * settings.height).toDouble()).toFloat()
        val anim = ViewAnimationUtils.createCircularReveal(view, settings.centerX, settings.centerY, initRadius, 0f)
        anim.run {
            duration = SHORT_DURATION
            interpolator = FastOutSlowInInterpolator()
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    view.visibility = View.GONE
                    listener?.let { it() }
                }
            })
            start()
        }
        startBackgroundAnimation(view, SHORT_DURATION)
    }

    private fun startBackgroundAnimation(view: View, animDuration: Long) {
        ValueAnimator().run {
            duration = animDuration
            setIntValues(BG_COLOR, BG_COLOR)
            setEvaluator(ArgbEvaluator())
            addUpdateListener { animator ->
                view.setBackgroundColor(animator.animatedValue as Int)
            }
            start()
        }
    }

    @SuppressLint("ParcelCreator")
    class AnimationSettings(
        var centerX: Int,
        var centerY: Int,
        var width: Int,
        var height: Int) : AutoParcelable

}