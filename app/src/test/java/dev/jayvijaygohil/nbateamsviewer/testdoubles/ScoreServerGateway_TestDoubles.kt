package dev.jayvijaygohil.nbateamsviewer.testdoubles

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import dev.jayvijaygohil.nbateamsviewer.data.network.ScoreServerGateway
import dev.jayvijaygohil.nbateamsviewer.getSuccessDummyResponse
import dev.jayvijaygohil.nbateamsviewer.model.Team
import io.reactivex.Single

class ScoreServerGatewayStub(
    val mock: ScoreServerGateway = mock()
) : ScoreServerGateway by mock {
    override fun getTeamList(): Single<List<Team>> {
        return Single.just(getSuccessDummyResponse()).also {
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