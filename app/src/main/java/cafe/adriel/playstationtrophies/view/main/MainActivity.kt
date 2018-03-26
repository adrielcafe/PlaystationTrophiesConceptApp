package cafe.adriel.playstationtrophies.view.main

import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import cafe.adriel.playstationtrophies.R
import cafe.adriel.playstationtrophies.view.BaseActivity
import cafe.adriel.playstationtrophies.view.main.sections.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vSections.adapter = SectionAdapter(supportFragmentManager)
        vSections.offscreenPageLimit = SectionAdapter.SECTION_SIZE

        vBottomNav.setupWithViewPager(vSections, true)
        vBottomNav.enableShiftingMode(false)
        vBottomNav.enableItemShiftingMode(false)
        vBottomNav.setTextVisibility(false)
    }

    private class SectionAdapter(fragManager: FragmentManager) : FragmentPagerAdapter(fragManager) {
        companion object {
            const val SECTION_SIZE = 5
        }

        override fun getItem(position: Int) =
            when(position){
                0 -> HomeSectionFragment.newInstance()
                1 -> StoreSectionFragment.newInstance()
                2 -> ChatSectionFragment.newInstance()
                3 -> NewsSectionFragment.newInstance()
                else -> OthersSectionFragment.newInstance()
            }

        override fun getCount() = SECTION_SIZE

    }
}
