package cafe.adriel.playstationtrophies.view.trophy.detail

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.view.View
import cafe.adriel.playstationtrophies.GlideApp
import cafe.adriel.playstationtrophies.R
import cafe.adriel.playstationtrophies.model.entity.Trophy
import cafe.adriel.playstationtrophies.view.BaseActivity
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.android.synthetic.main.activity_trophy_detail.*


class TrophyDetailActivity : BaseActivity() {

    companion object {
        const val EXTRA_TROPHY = "trophy"

        fun start(activity: Activity, trophy: Trophy, vararg sharedViews: View){
            val intent = Intent(activity, TrophyDetailActivity::class.java).run {
                putExtra(EXTRA_TROPHY, trophy)
            }
            val sharedViewPairs = sharedViews.map {
                Pair(it, it.transitionName)
            }.toTypedArray()
            val options = ActivityOptionsCompat
                .makeSceneTransitionAnimation(activity, *sharedViewPairs)
                .toBundle()
            activity.startActivity(intent, options)
        }
    }

    private lateinit var trophy: Trophy

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trophy_detail)
        postponeEnterTransition()

        if(intent.hasExtra(EXTRA_TROPHY)) {
            trophy = intent.getParcelableExtra(EXTRA_TROPHY)
        } else {
            throw IllegalArgumentException("Trophy not found")
        }

        vClose.setOnClickListener { supportFinishAfterTransition() }
        vGameTitle.text = trophy.game.title
        vBronzeTrophies.setValue(trophy.bronze)
        vSilverTrophies.setValue(trophy.silver)
        vGoldTrophies.setValue(trophy.gold)
        vPlatinumTrophies.setValue(trophy.platinum)
        vTrophiesProgress.animateValue(trophy.getUnlockedTrophiesPercent())
        vTotalTrophiesCount.text = "${trophy.getUnlockedTrophiesSum()}/${trophy.max}"
        vTotalTrophiesPercent.text = "${trophy.getUnlockedTrophiesPercent()}%"

        GlideApp.with(this)
            .load(trophy.game.getCoverResId())
            .transform(CenterCrop())
            .into(vGameCover)
        GlideApp.with(this)
            .load(trophy.game.getCoverResId())
            .transforms(CenterCrop(), BlurTransformation(15, 2))
            .into(object : SimpleTarget<Drawable>(){
                override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                    vBlurredGameCover.setImageDrawable(resource)
                    startPostponedEnterTransition()
                }
            })
    }

}