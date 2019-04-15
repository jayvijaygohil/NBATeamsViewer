package dev.jayvijaygohil.nbateamsviewer.ui.teamlist

import android.annotation.SuppressLint
import dev.jayvijaygohil.nbateamsviewer.common.IoScheduler
import dev.jayvijaygohil.nbateamsviewer.common.MainThreadScheduler
import dev.jayvijaygohil.nbateamsviewer.domain.entity.Team
import dev.jayvijaygohil.nbateamsviewer.domain.usecase.ShowAllTeamsUseCase
import dev.jayvijaygohil.nbateamsviewer.domain.usecase.SortTeamsUseCase
import dev.jayvijaygohil.nbateamsviewer.domain.usecase.SortTeamsUseCase.SortType
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class TeamListPresenter @Inject constructor(
    private val showAllTeamsUseCase: ShowAllTeamsUseCase,
    private val sortTeamsUseCase: SortTeamsUseCase,
    @IoScheduler private val subscribeScheduler: Scheduler,
    @MainThreadScheduler private val observeScheduler: Scheduler
) : TeamListContract.Presenter {
    private lateinit var view: TeamListContract.View
    private val disposables = CompositeDisposable()

    override fun attachView(view: TeamListContract.View) {
        this.view = view
    }

    override fun detachView() {
        disposables.dispose()
    }

    @SuppressLint("CheckResult")
    override fun fetchTeams() {
        showAllTeamsUseCase
            .execute()
            .subscribeOn(subscribeScheduler)
            .observeOn(observeScheduler)
            .subscribe { list, error ->
                error?.let { _ ->
                    // A common error keeping in mind the scope of this challenge.
                    // Error handling can be made granular with Interceptors throwing custom exceptions.
                    // Should not handle it manually by wrapping the return type with Response type.
                    view.showError()
                } ?: run {
                    view.showTeams(list)
                }
            }.let { disposables.add(it) }
    }

    @SuppressLint("CheckResult")
    override fun sortTeams(list: List<Team>, newSortType: SortType) {
        sortTeamsUseCase
            .execute(list, newSortType)
            .subscribeOn(subscribeScheduler)
            .observeOn(subscribeScheduler)
            .subscribe { sortedList, error ->
                error?.let {
                    view.showError()
                } ?: run {
                    view.showTeams(sortedList)
                }
            }.let { disposables.add(it) }
    }
}