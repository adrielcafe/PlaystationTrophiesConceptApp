package cafe.adriel.playstationtrophies.view.trophy.list

import cafe.adriel.playstationtrophies.model.entity.Trophy
import cafe.adriel.playstationtrophies.view.IView

interface ITrophyListView : IView {

    fun showTrophies(trophies: List<Trophy>)

}