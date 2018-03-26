package cafe.adriel.playstationtrophies.view

import android.content.Context
import com.arellomobile.mvp.MvpAppCompatActivity
import com.tsengvn.typekit.TypekitContextWrapper

abstract class BaseActivity : MvpAppCompatActivity(), IView {

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase))
    }

}