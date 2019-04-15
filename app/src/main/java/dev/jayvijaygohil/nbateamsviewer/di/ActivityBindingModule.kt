package dev.jayvijaygohil.nbateamsviewer.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dev.jayvijaygohil.nbateamsviewer.common.SingleActivityScope
import dev.jayvijaygohil.nbateamsviewer.ui.SingleActivity
import dev.jayvijaygohil.nbateamsviewer.ui.SingleActivityModule


@Module
abstract class ActivityBindingModule {
    @ContributesAndroidInjector(modules = [SingleActivityModule::class, FragmentBindingModule::class])
    @SingleActivityScope
    abstract fun bindSingleActivity(): SingleActivity
}