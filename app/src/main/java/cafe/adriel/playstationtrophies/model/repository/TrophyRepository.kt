package cafe.adriel.playstationtrophies.model.repository

import cafe.adriel.playstationtrophies.App
import cafe.adriel.playstationtrophies.R
import cafe.adriel.playstationtrophies.model.entity.Trophy
import com.afollestad.ason.Ason
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object TrophyRepository {

    // Yes, I know that there's a possible memory leak here :P
    // But in a production ready app we'll get the data from a
    // local database or a REST API, and not from the /res/raw folder
    fun getTrophies(): Observable<List<Trophy>> =
        Observable.fromCallable {
                App.context.resources
                    .openRawResource(R.raw.trophies)
                    .bufferedReader()
                    .readText()
            }
            .map { json ->
                Ason.deserializeList(json, Trophy::class.java)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

}