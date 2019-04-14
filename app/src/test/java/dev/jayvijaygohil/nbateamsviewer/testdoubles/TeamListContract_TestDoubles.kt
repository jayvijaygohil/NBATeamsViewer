package dev.jayvijaygohil.nbateamsviewer.testdoubles

import dev.jayvijaygohil.nbateamsviewer.ui.teamlist.TeamListContract
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import dev.jayvijaygohil.nbateamsviewer.model.Team

class TeamListContractViewStub(
    val mock: TeamListContract.View = mock()
): TeamListContract.View by mock {
    lateinit var list: List<Team>

    override fun showTeams(list: List<Team>) {
        this.list = list
        mock.showTeams(list)
    }

    override fun showError() {
        mock.showError()
    }

    fun verifyShowAllTeamsCalled(timesCalled: Int) {
        verify(mock, times(timesCalled)).showTeams(list)
    }

    fun verifyShowErrorCalled(timesCalled: Int) {
        verify(mock, times(timesCalled)).showError()
    }

    fun verifyNoNewInteractions() {
        verifyNoMoreInteractions(mock)
    }
}