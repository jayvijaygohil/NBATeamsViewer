package dev.jayvijaygohil.nbateamsviewer.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dev.jayvijaygohil.nbateamsviewer.NbaApplication
import dev.jayvijaygohil.nbateamsviewer.common.ApplicationScope

@ApplicationScope
@Component(modules = [ApplicationModule::class, ActivityBindingModule::class, AndroidInjectionModule::class])
interface ApplicationComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance fun newApplicationModule(application: Application): Builder
        fun build(): ApplicationComponent
    }

    fun inject(instance: NbaApplication)
}