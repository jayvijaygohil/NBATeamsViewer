package dev.jayvijaygohil.nbateamsviewer.ui.teamlist

import dev.jayvijaygohil.nbateamsviewer.model.Team
import dev.jayvijaygohil.nbateamsviewer.testdoubles.ShowAllTeamsUseCaseStub
import dev.jayvijaygohil.nbateamsviewer.testdoubles.SortTeamsUseCaseStub
import dev.jayvijaygohil.nbateamsviewer.testdoubles.TeamListContractViewStub
import dev.jayvijaygohil.nbateamsviewer.usecase.SortTeamsUseCase.SortType.NAME_DESC
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test

class TeamListPresenterTest {
    private lateinit var presenter: TeamListPresenter
    private lateinit var view: TeamListContractViewStub
    private lateinit var showAllTeamsUseCase: ShowAllTeamsUseCaseStub
    private lateinit var sortTeamsUseCase: SortTeamsUseCaseStub

    @Before
    fun setUp() {
        val trampolineScheduler = Schedulers.trampoline()
        showAllTeamsUseCase = ShowAllTeamsUseCaseStub()
        sortTeamsUseCase = SortTeamsUseCaseStub()

        view = TeamListContractViewStub()
        presenter = TeamListPresenter(showAllTeamsUseCase, sortTeamsUseCase, trampolineScheduler, trampolineScheduler)
        presenter.attachView(view)
    }

    @Test
    fun `test showAllTeamsUseCase_execute is invoked`() {
        presenter.fetchTeams()

        showAllTeamsUseCase.verifyExecuteCalled(1)
        showAllTeamsUseCase.verifyNoNewInteractions()
    }

    @Test
    fun `test view_execute is invoked when showAllTeamsUseCase_execute is successful`() {
        showAllTeamsUseCase.isError = false

        presenter.fetchTeams()

        view.verifyShowAllTeamsCalled(1)
        view.verifyNoNewInteractions()
    }

    @Test
    fun `test view_error is invoked when showAllTeamsUseCase_execute fails`() {
        showAllTeamsUseCase.isError = true

        presenter.fetchTeams()

        view.verifyShowErrorCalled(1)
        view.verifyNoNewInteractions()

        showAllTeamsUseCase.isError = false
    }

    @Test
    fun `test sortTeamsUseCase_execute is invoked with proper parameters`() {
        val list = emptyList<Team>()

        presenter.sortTeams(list, NAME_DESC)

        sortTeamsUseCase.verifyExecuteCalled(1, list, NAME_DESC)
        sortTeamsUseCase.verifyNoNewInteractions()
    }

    @Test
    fun `test view_execute is invoked when sortTeamsUseCase_execute is successful`() {
        val list = emptyList<Team>()
        sortTeamsUseCase.isError = false

        presenter.sortTeams(list, NAME_DESC)

        view.verifyShowAllTeamsCalled(1)
        view.verifyNoNewInteractions()
    }

    @Test
    fun `test view_error is invoked when sortTeamsUseCase_execute fails`() {
        val list = emptyList<Team>()
        sortTeamsUseCase.isError = true

        presenter.sortTeams(list, NAME_DESC)

        view.verifyShowErrorCalled(1)
        view.verifyNoNewInteractions()

        sortTeamsUseCase.isError = false
    }
}