package dev.jayvijaygohil.nbateamsviewer.ui

import android.content.Context
import dagger.Module
import dagger.Provides
import dev.jayvijaygohil.nbateamsviewer.common.SingleActivityScope
import dev.jayvijaygohil.nbateamsviewer.ui.SingleActivity
import dev.jayvijaygohil.nbateamsviewer.ui.SingleActivityContract

@Module
class SingleActivityModule {
    @Provides
    @SingleActivityScope
    fun provideSingleActivityViewContract(context: Context): SingleActivityContract.View {
        return (context as SingleActivityContract.View)
    }

    @Provides
    @SingleActivityScope
    fun provideSingleActivityContext(activity: SingleActivity): Context {
        return activity
    }
}