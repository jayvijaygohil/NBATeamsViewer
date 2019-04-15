package dev.jayvijaygohil.nbateamsviewer

import android.app.Application
import dev.jayvijaygohil.nbateamsviewer.di.ApplicationComponent
import dev.jayvijaygohil.nbateamsviewer.di.ApplicationModule
import dev.jayvijaygohil.nbateamsviewer.di.DaggerApplicationComponent

class NbaApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent
        private set

    override fun onCreate() {
        super.onCreate()

        applicationComponent =
            DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }
}