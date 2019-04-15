package dev.jayvijaygohil.nbateamsviewer.testdoubles

import dev.jayvijaygohil.nbateamsviewer.domain.repository.ScoreServerRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import dev.jayvijaygohil.nbateamsviewer.domain.entity.Team
import io.reactivex.Single

class ScoreServerRepositoryStub(
    val mock: ScoreServerRepository = mock()
) : ScoreServerRepository by mock {
    var list: List<Team> = emptyList()

    override fun getAllTeams(): Single<List<Team>> {
        return Single.just(list).also {
            mock.getAllTeams()
        }
    }

    fun verifyGetAllTeamsCalled(timesCalled: Int) {
        verify(mock, times(timesCalled)).getAllTeams()
    }

    fun verifyNoNewInteractions() {
        verifyNoMoreInteractions(mock)
    }
}