package dev.jayvijaygohil.nbateamsviewer.di

import dagger.Component
import dev.jayvijaygohil.nbateamsviewer.common.ApplicationScope

@ApplicationScope
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun newPresentationComponent(presentationModule: PresentationModule): PresentationComponent
}