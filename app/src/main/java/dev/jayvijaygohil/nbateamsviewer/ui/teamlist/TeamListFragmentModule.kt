package dev.jayvijaygohil.nbateamsviewer.ui.teamlist

import dagger.Module
import dagger.Provides
import dev.jayvijaygohil.nbateamsviewer.common.IoScheduler
import dev.jayvijaygohil.nbateamsviewer.common.MainThreadScheduler
import dev.jayvijaygohil.nbateamsviewer.domain.data.network.ScoreServerGateway
import dev.jayvijaygohil.nbateamsviewer.domain.repository.ScoreServerRepository
import dev.jayvijaygohil.nbateamsviewer.domain.repository.ScoreServerRepositoryImpl
import dev.jayvijaygohil.nbateamsviewer.domain.usecase.ShowAllTeamsUseCase
import dev.jayvijaygohil.nbateamsviewer.domain.usecase.ShowAllTeamsUseCaseImpl
import dev.jayvijaygohil.nbateamsviewer.domain.usecase.SortTeamsUseCase
import dev.jayvijaygohil.nbateamsviewer.domain.usecase.SortTeamsUseCaseImpl
import dev.jayvijaygohil.nbateamsviewer.ui.SingleActivityContract
import io.reactivex.Scheduler

@Module
class TeamListFragmentModule {
    @Provides
    fun provideTeamListAdapter(singleActivityViewContract: SingleActivityContract.View): TeamListAdapter {
        return TeamListAdapter(singleActivityViewContract)
    }

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
    fun provideShowAllTeamsUseCase(repository: ScoreServerRepository): ShowAllTeamsUseCase {
        return ShowAllTeamsUseCaseImpl(repository)
    }

    @Provides
    fun provideSortTeamsListUseCase(): SortTeamsUseCase {
        return SortTeamsUseCaseImpl()
    }

    @Provides
    fun provideScoreServerRepository(gateway: ScoreServerGateway): ScoreServerRepository {
        return ScoreServerRepositoryImpl(gateway)
    }
}