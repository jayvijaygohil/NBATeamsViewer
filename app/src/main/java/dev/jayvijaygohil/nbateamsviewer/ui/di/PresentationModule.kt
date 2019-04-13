package dev.jayvijaygohil.nbateamsviewer.ui.di

import android.app.Activity
import android.content.Context
import androidx.fragment.app.FragmentActivity
import dagger.Module
import dagger.Provides
import dev.jayvijaygohil.nbateamsviewer.data.network.ScoreServerGateway
import dev.jayvijaygohil.nbateamsviewer.data.network.ScoreServerRepository
import dev.jayvijaygohil.nbateamsviewer.data.network.ScoreServerRepositoryImpl
import dev.jayvijaygohil.nbateamsviewer.usecase.ShowAllTeamsUseCase
import dev.jayvijaygohil.nbateamsviewer.usecase.ShowAllTeamsUseCaseImpl
import dev.jayvijaygohil.nbateamsviewer.usecase.SortTeamsListUseCase
import dev.jayvijaygohil.nbateamsviewer.usecase.SortTeamsUseCaseImpl

@Module
class PresentationModule(private val activity: FragmentActivity) {
    @Provides
    fun provideSortTeamsListUseCase(): SortTeamsListUseCase {
        return SortTeamsUseCaseImpl()
    }

    @Provides
    fun provideShowAllTeamsUseCase(repository: ScoreServerRepository): ShowAllTeamsUseCase {
        return ShowAllTeamsUseCaseImpl(repository)
    }

    @Provides
    fun provideScoreServerRepository(gateway: ScoreServerGateway): ScoreServerRepository {
        return ScoreServerRepositoryImpl(gateway)
    }

    @Provides
    fun provideContext(activity: Activity): Context {
        return activity
    }
}