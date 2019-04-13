package dev.jayvijaygohil.nbateamsviewer.common

import androidx.annotation.UiThread
import androidx.appcompat.app.AppCompatActivity
import dev.jayvijaygohil.nbateamsviewer.application.NbaApplication
import dev.jayvijaygohil.nbateamsviewer.ui.di.PresentationComponent
import dev.jayvijaygohil.nbateamsviewer.ui.di.PresentationModule


abstract class DaggerActivity : AppCompatActivity() {
    private var mIsInjectorUsed: Boolean = false
    private val applicationComponent = (application as NbaApplication).applicationComponent

    protected val presentationComponent: PresentationComponent
        @UiThread
        get() {
            if (mIsInjectorUsed) {
                throw RuntimeException("There is no need to use injector more than once")
            }
            mIsInjectorUsed = true
            return applicationComponent
                .newPresentationComponent(PresentationModule(this))
        }
}