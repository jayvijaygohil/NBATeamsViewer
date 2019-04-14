package dev.jayvijaygohil.nbateamsviewer.ui.di

import android.app.Activity
import android.content.Context
import androidx.fragment.app.FragmentActivity
import dagger.Module
import dagger.Provides
import dev.jayvijaygohil.nbateamsviewer.common.IoScheduler
import dev.jayvijaygohil.nbateamsviewer.common.MainThreadScheduler
import dev.jayvijaygohil.nbateamsviewer.data.network.ScoreServerGateway
import dev.jayvijaygohil.nbateamsviewer.data.network.ScoreServerRepository
import dev.jayvijaygohil.nbateamsviewer.data.network.ScoreServerRepositoryImpl
import dev.jayvijaygohil.nbateamsviewer.ui.SingleActivityContract
import dev.jayvijaygohil.nbateamsviewer.ui.teamdetail.TeamDetailAdapter
import dev.jayvijaygohil.nbateamsviewer.ui.teamlist.TeamListAdapter
import dev.jayvijaygohil.nbateamsviewer.ui.teamlist.TeamListContract
import dev.jayvijaygohil.nbateamsviewer.ui.teamlist.TeamListPresenter
import dev.jayvijaygohil.nbateamsviewer.usecase.ShowAllTeamsUseCase
import dev.jayvijaygohil.nbateamsviewer.usecase.ShowAllTeamsUseCaseImpl
import dev.jayvijaygohil.nbateamsviewer.usecase.SortTeamsUseCase
import dev.jayvijaygohil.nbateamsviewer.usecase.SortTeamsUseCaseImpl
import io.reactivex.Scheduler

@Module
class PresentationModule(private val activity: FragmentActivity) {

    @Provides
    fun provideTeamListPresenter(
            showAllTeamsUseCase: ShowAllTeamsUseCase,
            sortTeamsUseCase: SortTeamsUseCase,
            @IoScheduler subscribeScheduler: Scheduler,
            @MainThreadScheduler observeScheduler: Scheduler
    ): TeamListContract.Presenter {
        return TeamListPresenter(
                showAllTeamsUseCase,
                sortTeamsUseCase,
                subscribeScheduler,
                observeScheduler
        )
    }

    @Provides
    fun provideTeamListAdapter(singleActivityViewContract: SingleActivityContract.View): TeamListAdapter {
        return TeamListAdapter(singleActivityViewContract)
    }

    @Provides
    fun provideTeamDetailAdapter(): TeamDetailAdapter {
        return TeamDetailAdapter()
    }

    @Provides
    fun provideSortTeamsListUseCase(): SortTeamsUseCase {
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
    fun provideSingleActivityViewContract(): SingleActivityContract.View {
        return (activity as SingleActivityContract.View)
    }

    @Provides
    fun provideSingleActivityContext(): Context {
        return activity
    }

    @Provides
    fun provideSingleActivity(): Activity {
        return activity
    }
}