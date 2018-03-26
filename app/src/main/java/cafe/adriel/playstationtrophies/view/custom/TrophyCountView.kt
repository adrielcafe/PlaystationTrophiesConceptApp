package cafe.adriel.playstationtrophies.view.custom

import android.content.Context
import android.content.res.ColorStateList
import android.support.v7.widget.LinearLayoutCompat
import android.util.AttributeSet
import cafe.adriel.playstationtrophies.R
import cafe.adriel.playstationtrophies.model.entity.TrophyType
import kotlinx.android.synthetic.main.view_trophy_count.view.*

class TrophyCountView(context: Context, attrs: AttributeSet? = null) : LinearLayoutCompat(context, attrs) {

    private var trophyType: TrophyType? = null

    init {
        inflate(getContext(), R.layout.view_trophy_count, this)
        if(!isInEditMode){
            if (attrs != null) {
                val styleAttrs = getContext().obtainStyledAttributes(attrs, R.styleable.TrophyCountView)
                if(styleAttrs.hasValue(R.styleable.TrophyCountView_tcv_type)) {
                    val enumValue = styleAttrs.getInt(R.styleable.TrophyCountView_tcv_type, -1)
                    if(enumValue >= 0)
                        trophyType = TrophyType.values()[enumValue]
                }
                styleAttrs.recycle()
            }

            trophyType?.let {
                vTrophyIcon.imageTintList = ColorStateList.valueOf(it.color)
                vTrophyCount.setTextColor(it.color)
            }
        }
    }

    fun setValue(value: Int) = vTrophyCount.animateValue(value)

}