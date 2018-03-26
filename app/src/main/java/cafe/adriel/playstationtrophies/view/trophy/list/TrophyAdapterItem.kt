package cafe.adriel.playstationtrophies.view.trophy.list

import android.graphics.drawable.ColorDrawable
import android.support.v7.widget.RecyclerView
import android.view.View
import cafe.adriel.playstationtrophies.GlideApp
import cafe.adriel.playstationtrophies.R
import cafe.adriel.playstationtrophies.colorFrom
import cafe.adriel.playstationtrophies.model.entity.Trophy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.mikepenz.fastadapter.items.AbstractItem
import kotlinx.android.synthetic.main.list_item_trophy.view.*

class TrophyAdapterItem(val trophy: Trophy) : AbstractItem<TrophyAdapterItem, TrophyAdapterItem.ViewHolder>(){

    companion object {
        private val coverPlaceholder = ColorDrawable(colorFrom(R.color.lighter_gray))
    }

    private var alreadyAnimated = false

    override fun getIdentifier() = trophy.game.id

    override fun getType() = layoutRes

    override fun getLayoutRes() = R.layout.list_item_trophy

    override fun getViewHolder(v: View) = ViewHolder(v)

    override fun bindView(holder: ViewHolder, payloads: MutableList<Any>) {
        super.bindView(holder, payloads)
        holder.itemView?.apply {
            vGameTitle.text = trophy.game.title
            vTrophiesPercentage.text = "${trophy.getUnlockedTrophiesPercent()}%"
            GlideApp.with(context)
                .load(trophy.game.getCoverResId())
                .override(120, 120)
                .placeholder(coverPlaceholder)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(vGameCover)

            if(alreadyAnimated){
                vTrophiesProgress.progress = trophy.getUnlockedTrophiesPercent()
            } else {
                vTrophiesProgress.animateValue(trophy.getUnlockedTrophiesPercent())
                alreadyAnimated = true
            }
        }
    }

    override fun unbindView(holder: ViewHolder) {
        super.unbindView(holder)
        holder.itemView?.apply {
            vGameTitle.text = ""
            vTrophiesPercentage.text = "0"
            vTrophiesProgress.progress = 0
            GlideApp.with(context).clear(vGameCover)
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

}