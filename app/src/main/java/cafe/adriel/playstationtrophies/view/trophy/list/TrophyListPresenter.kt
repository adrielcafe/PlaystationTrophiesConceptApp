package cafe.adriel.playstationtrophies.view.trophy.list

import cafe.adriel.playstationtrophies.model.repository.TrophyRepository
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter

@InjectViewState
class TrophyListPresenter : MvpPresenter<ITrophyListView>() {

    fun loadTrophies(){
        TrophyRepository.getTrophies()
            .subscribe(viewState::showTrophies, Throwable::printStackTrace)
    }

}