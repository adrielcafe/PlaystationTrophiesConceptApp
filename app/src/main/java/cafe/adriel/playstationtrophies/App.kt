package cafe.adriel.playstationtrophies

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.tsengvn.typekit.Typekit

class App : Application() {

    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = this

        Typekit.getInstance()
            .addNormal(Typekit.createFromAsset(this, "fonts/Exo-Regular.ttf"))
            .addItalic(Typekit.createFromAsset(this, "fonts/Exo-Italic.ttf"))
            .addBold(Typekit.createFromAsset(this, "fonts/Exo-Bold.ttf"))
            .addBoldItalic(Typekit.createFromAsset(this, "fonts/Exo-BoldItalic.ttf"))
            .addCustom1(Typekit.createFromAsset(this, "fonts/Exo-Light.ttf"))
    }

}