package dev.jayvijaygohil.nbateamsviewer.application.di

import dagger.Component
import dev.jayvijaygohil.nbateamsviewer.common.ApplicationScope
import dev.jayvijaygohil.nbateamsviewer.ui.di.PresentationComponent
import dev.jayvijaygohil.nbateamsviewer.ui.di.PresentationModule

@ApplicationScope
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun newPresentationComponent(presentationModule: PresentationModule): PresentationComponent
}