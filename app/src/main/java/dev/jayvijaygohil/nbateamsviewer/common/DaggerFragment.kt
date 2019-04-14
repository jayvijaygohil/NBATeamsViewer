package dev.jayvijaygohil.nbateamsviewer.common

import androidx.annotation.UiThread
import androidx.fragment.app.Fragment
import dev.jayvijaygohil.nbateamsviewer.application.NbaApplication
import dev.jayvijaygohil.nbateamsviewer.application.di.ApplicationComponent
import dev.jayvijaygohil.nbateamsviewer.ui.di.PresentationComponent
import dev.jayvijaygohil.nbateamsviewer.ui.di.PresentationModule


abstract class DaggerFragment : Fragment() {
    private var mIsInjectorUsed: Boolean = false
    private lateinit var applicationComponent: ApplicationComponent
    private lateinit var presentationComponent: PresentationComponent

    // Always call this method inside onAttach or else the application reference will be null
    @UiThread
    protected fun component(): PresentationComponent {
        applicationComponent = (activity!!.application!! as NbaApplication).applicationComponent

        presentationComponent = if (mIsInjectorUsed) {
            throw RuntimeException("There is no need to use injector more than once")
        } else {
            mIsInjectorUsed = true
            applicationComponent
                    .newPresentationComponent(PresentationModule(activity!!))
        }

        return presentationComponent
    }
}