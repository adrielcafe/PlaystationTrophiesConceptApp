package cafe.adriel.playstationtrophies.view.custom

import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.widget.ProgressBar

class AnimatedProgressBar(context: Context, attrs: AttributeSet? = null) : ProgressBar(context, attrs) {

    private val DURATION = 500L
    private val START_DELAY = 250L

    private val animator: ValueAnimator

    init {
        if(!isInEditMode) {
            progress = 0
            max = 100
            animator = ValueAnimator().apply {
                duration = DURATION
                startDelay = START_DELAY
                addUpdateListener({
                    progress = it.animatedValue.toString().toInt()
                })
            }
        } else {
            animator = ValueAnimator()
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        animator.cancel()
    }

    fun animateValue(value: Int) {
        animator.setIntValues(0, value)
        animator.start()
    }

}