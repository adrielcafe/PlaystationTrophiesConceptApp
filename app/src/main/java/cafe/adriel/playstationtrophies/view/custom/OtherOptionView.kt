package cafe.adriel.playstationtrophies.view.custom

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.support.v7.widget.LinearLayoutCompat
import android.util.AttributeSet
import cafe.adriel.playstationtrophies.GlideApp
import cafe.adriel.playstationtrophies.R
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.view_other_option.view.*

class OtherOptionView(context: Context, attrs: AttributeSet? = null) : LinearLayoutCompat(context, attrs) {

    private var title = ""
    private var icon = ColorDrawable(Color.TRANSPARENT) as Drawable
    private var tintIcon = true
    private var roundIcon = false

    init {
        inflate(getContext(), R.layout.view_other_option, this)
        if(!isInEditMode) {
            if (attrs != null) {
                val styleAttrs =
                    getContext().obtainStyledAttributes(attrs, R.styleable.OtherOptionView)
                title = styleAttrs.getString(R.styleable.OtherOptionView_oov_title)
                icon = styleAttrs.getDrawable(R.styleable.OtherOptionView_oov_icon)
                tintIcon = styleAttrs.getBoolean(R.styleable.OtherOptionView_oov_tint_icon, true)
                roundIcon = styleAttrs.getBoolean(R.styleable.OtherOptionView_oov_round_icon, false)
                styleAttrs.recycle()
            }

            vTitle.text = title
            if (!tintIcon) vIcon.imageTintList = null
            if (roundIcon) {
                GlideApp.with(this)
                    .load(icon)
                    .apply(RequestOptions.circleCropTransform())
                    .into(vIcon)
            } else {
                GlideApp.with(this)
                    .load(icon)
                    .into(vIcon)
            }
        }
    }

}