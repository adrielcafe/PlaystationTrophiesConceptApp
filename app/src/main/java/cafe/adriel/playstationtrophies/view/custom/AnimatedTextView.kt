package cafe.adriel.playstationtrophies.view.custom

import android.animation.ValueAnimator
import android.content.Context
import android.support.v7.widget.AppCompatTextView
import android.util.AttributeSet
import cafe.adriel.playstationtrophies.R

class AnimatedTextView(context: Context, attrs: AttributeSet? = null) : AppCompatTextView(context, attrs) {

    private val DURATION = 1000L
    private val START_DELAY = 250L

    private val animator: ValueAnimator
    private var suffix = ""

    init {
        if(!isInEditMode) {
            if (attrs != null) {
                val styledAttrs =
                    getContext().obtainStyledAttributes(attrs, R.styleable.AnimatedTextView)
                if(styledAttrs.hasValue(R.styleable.AnimatedTextView_atv_suffix))
                    suffix = styledAttrs.getString(R.styleable.AnimatedTextView_atv_suffix)
                styledAttrs.recycle()
            }
            animator = ValueAnimator().apply {
                duration = DURATION
                startDelay = START_DELAY
                addUpdateListener({
                    text = it.animatedValue.toString()
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

    override fun setText(text: CharSequence?, type: BufferType?) {
        if(suffix == null) suffix = ""
        super.setText("$text$suffix", type)
    }

    fun animateValue(value: Int) {
        animator.setIntValues(0, value)
        animator.start()
    }

}