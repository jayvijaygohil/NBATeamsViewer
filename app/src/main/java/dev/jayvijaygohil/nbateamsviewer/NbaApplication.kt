package dev.jayvijaygohil.nbateamsviewer

import android.app.Activity
import android.app.Application
import dev.jayvijaygohil.nbateamsviewer.di.ApplicationComponent
import dev.jayvijaygohil.nbateamsviewer.di.ApplicationModule
import dev.jayvijaygohil.nbateamsviewer.di.DaggerApplicationComponent

class NbaApplication : Application() {
    private lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        applicationComponent =
            DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

    fun getApplicationComponent() = applicationComponent

    companion object {
        fun get(activity: Activity) = activity.application
    }
}