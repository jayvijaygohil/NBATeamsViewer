package dev.jayvijaygohil.nbateamsviewer.testdoubles

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import dev.jayvijaygohil.nbateamsviewer.domain.data.network.ScoreServerGateway
import dev.jayvijaygohil.nbateamsviewer.domain.entity.Team
import io.reactivex.Single

class ScoreServerGatewayStub(
    val mock: ScoreServerGateway = mock()
) : ScoreServerGateway by mock {
    var list: List<Team> = emptyList()

    override fun getTeamList(): Single<List<Team>> {
        return Single.just(list).also {
            mock.getTeamList()
        }
    }

    fun verifyGetTeamListCalled(timesCalled: Int) {
        verify(mock, times(timesCalled)).getTeamList()
    }

    fun verifyNoNewInteractions() {
        verifyNoMoreInteractions(mock)
    }
}