package dev.jayvijaygohil.nbateamsviewer.ui.di

import dagger.Subcomponent
import dev.jayvijaygohil.nbateamsviewer.ui.MainActivity

@Subcomponent(modules = [PresentationModule::class])
interface PresentationComponent {
    fun inject(activity: MainActivity)
}