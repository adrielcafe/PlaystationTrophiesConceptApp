package cafe.adriel.playstationtrophies.model.entity

import cafe.adriel.playstationtrophies.R
import cafe.adriel.playstationtrophies.colorFrom

enum class TrophyType(val color: Int) {

    BRONZE(colorFrom(R.color.bronze)),
    SILVER(colorFrom(R.color.silver)),
    GOLD(colorFrom(R.color.gold)),
    PLATINUM(colorFrom(R.color.platinum))

}