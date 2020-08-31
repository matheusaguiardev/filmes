package br.com.aguiar.aguiarmovies.ui.app

import android.app.Application
import br.com.aguiar.aguiarmovies.ui.di.myModule
import com.orhanobut.hawk.Hawk
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            Hawk.init(this@MyApplication).build()
            modules(myModule)
        }
    }

}