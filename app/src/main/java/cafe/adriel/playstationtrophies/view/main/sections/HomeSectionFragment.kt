package cafe.adriel.playstationtrophies.view.main.sections

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import cafe.adriel.playstationtrophies.R
import cafe.adriel.playstationtrophies.view.BaseFragment

class HomeSectionFragment : BaseFragment() {

    companion object {
        fun newInstance() = HomeSectionFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        inflater.inflate(R.layout.fragment_section_home, container, false) as ViewGroup

}