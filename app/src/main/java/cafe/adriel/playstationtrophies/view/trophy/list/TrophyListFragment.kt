package cafe.adriel.playstationtrophies.view.trophy.list

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cafe.adriel.playstationtrophies.R
import cafe.adriel.playstationtrophies.model.entity.Trophy
import cafe.adriel.playstationtrophies.view.BaseFragment
import cafe.adriel.playstationtrophies.view.custom.RevealAnimationHelper
import cafe.adriel.playstationtrophies.view.trophy.detail.TrophyDetailActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter
import com.mikepenz.itemanimators.SlideUpAlphaAnimator
import kotlinx.android.synthetic.main.fragment_trophy_list.view.*
import kotlinx.android.synthetic.main.list_item_trophy.view.*

class TrophyListFragment : BaseFragment(), ITrophyListView {

    @InjectPresenter
    lateinit var presenter: TrophyListPresenter
    lateinit var revealSettings: RevealAnimationHelper.AnimationSettings

    companion object {
        const val EXTRA_REVEAL_SETTINGS = "revealSettings"

        fun newInstance(revealSettings: RevealAnimationHelper.AnimationSettings): TrophyListFragment {
            val frag = TrophyListFragment()
            frag.arguments = Bundle().apply {
                putParcelable(EXTRA_REVEAL_SETTINGS, revealSettings)
            }
            return frag
        }
    }

    private val adapter = FastItemAdapter<TrophyAdapterItem>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val vRoot = inflater.inflate(R.layout.fragment_trophy_list, container, false) as ViewGroup

        arguments?.run {
            revealSettings = getParcelable(EXTRA_REVEAL_SETTINGS)
            RevealAnimationHelper.startEnterAnimation(vRoot, revealSettings)
        }

        adapter.setHasStableIds(true)
        adapter.withOnClickListener { v, _, item, _ ->
            val trophy = item.trophy
            val sharedView = v!!.vGameCover
            TrophyDetailActivity.start(activity as Activity, trophy, sharedView)
            true
        }

        vRoot.run {
            vClose.setOnClickListener {
                RevealAnimationHelper.startExitAnimation(vRoot, revealSettings) {
                    fragmentManager?.popBackStack()
                }
            }

            vTrophies.setHasFixedSize(true)
            vTrophies.itemAnimator = SlideUpAlphaAnimator()
            vTrophies.adapter = adapter
        }

        presenter.loadTrophies()

        return vRoot
    }

    override fun showTrophies(trophies: List<Trophy>) {
        if(trophies.isNotEmpty()) {
            val items = trophies.map { TrophyAdapterItem(it) }
            adapter.clear()
                .add(items)
                .notifyAdapterDataSetChanged()
        }
    }

}