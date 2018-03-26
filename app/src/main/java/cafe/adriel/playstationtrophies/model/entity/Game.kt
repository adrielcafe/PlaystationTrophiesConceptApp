package cafe.adriel.playstationtrophies.model.entity

import android.annotation.SuppressLint
import cafe.adriel.playstationtrophies.App
import io.mironov.smuggler.AutoParcelable

@SuppressLint("ParcelCreator")
data class Game(
    val id: Long = 0L,
    val title: String = "") : AutoParcelable {

    fun getCoverResId() : Int {
        val resName = "cover_game_$id"
        return App.context.resources
            .getIdentifier(resName, "drawable", App.context.packageName)
    }

}