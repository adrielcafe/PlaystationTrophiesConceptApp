package cafe.adriel.playstationtrophies.view.main.sections

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cafe.adriel.playstationtrophies.R
import cafe.adriel.playstationtrophies.view.BaseFragment
import cafe.adriel.playstationtrophies.view.custom.RevealAnimationHelper
import cafe.adriel.playstationtrophies.view.trophy.list.TrophyListFragment
import kotlinx.android.synthetic.main.fragment_section_others.*
import kotlinx.android.synthetic.main.fragment_section_others.view.*

class OthersSectionFragment : BaseFragment(), View.OnClickListener {

    companion object {
        fun newInstance() = OthersSectionFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val vRoot = inflater.inflate(R.layout.fragment_section_others, container, false) as ViewGroup
        vRoot.let{
            it.vOptionMyProfile.setOnClickListener(this)
            it.vOptionPsPlus.setOnClickListener(this)
            it.vOptionGames.setOnClickListener(this)
            it.vOptionTrophies.setOnClickListener(this)
            it.vOptionSupport.setOnClickListener(this)
            it.vOptionGuides.setOnClickListener(this)
        }
        return vRoot
    }

    override fun onClick(v: View) {
        val revealSettings = RevealAnimationHelper.AnimationSettings(
            (v.x + v.width / 2).toInt(),
            v.y.toInt(),
            vContainer.width,
            vContainer.height
        )

        when(v.id){
            R.id.vOptionMyProfile -> return // TODO
            R.id.vOptionPsPlus -> return // TODO
            R.id.vOptionGames -> return // TODO
            R.id.vOptionTrophies -> showOption(TrophyListFragment.newInstance(revealSettings))
            R.id.vOptionSupport -> return // TODO
            R.id.vOptionGuides -> return // TODO
        }
    }

    private fun showOption(frag: Fragment) {
        fragmentManager?.beginTransaction()
            ?.replace(R.id.vContainer, frag)
            ?.addToBackStack(null)
            ?.commit()
    }

}