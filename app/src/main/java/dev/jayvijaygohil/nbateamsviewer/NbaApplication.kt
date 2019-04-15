package dev.jayvijaygohil.nbateamsviewer

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dev.jayvijaygohil.nbateamsviewer.di.DaggerApplicationComponent
import javax.inject.Inject

class NbaApplication : Application(), HasActivityInjector {
    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        DaggerApplicationComponent
                .builder()
                .newApplicationModule(this)
                .build()
                .inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityInjector
    }
}