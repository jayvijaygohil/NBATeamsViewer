package dev.jayvijaygohil.nbateamsviewer.application

import android.app.Application
import dev.jayvijaygohil.nbateamsviewer.application.di.ApplicationComponent
import dev.jayvijaygohil.nbateamsviewer.application.di.ApplicationModule
import dev.jayvijaygohil.nbateamsviewer.application.di.DaggerApplicationComponent

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