package dev.jayvijaygohil.nbateamsviewer.di

import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun getRetrofit(): Retrofit
}