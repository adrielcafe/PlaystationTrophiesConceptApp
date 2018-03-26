package cafe.adriel.playstationtrophies.model.entity

import android.annotation.SuppressLint
import io.mironov.smuggler.AutoParcelable

@SuppressLint("ParcelCreator")
data class Trophy(
    val game: Game = Game(),
    val max: Int = 0,
    val bronze: Int = 0,
    val silver: Int = 0,
    val gold: Int = 0,
    val platinum: Int = 0) : AutoParcelable {

    fun getUnlockedTrophiesSum() = bronze + silver + gold + platinum

    fun getUnlockedTrophiesPercent() = getUnlockedTrophiesSum() * 100 / max

}