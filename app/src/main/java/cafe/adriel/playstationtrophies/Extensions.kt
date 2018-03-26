package cafe.adriel.playstationtrophies

import android.support.annotation.ColorRes
import android.support.v4.content.ContextCompat

fun colorFrom(@ColorRes colorRes: Int) = ContextCompat.getColor(App.context, colorRes)