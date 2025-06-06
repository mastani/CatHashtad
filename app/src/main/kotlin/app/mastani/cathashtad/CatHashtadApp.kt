package app.mastani.cathashtad

import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class CatHashtadApp : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}