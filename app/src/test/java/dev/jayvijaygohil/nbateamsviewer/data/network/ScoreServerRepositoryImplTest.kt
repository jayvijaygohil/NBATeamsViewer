package dev.jayvijaygohil.nbateamsviewer.data.network

import dev.jayvijaygohil.nbateamsviewer.domain.repository.ScoreServerRepository
import dev.jayvijaygohil.nbateamsviewer.domain.repository.ScoreServerRepositoryImpl
import dev.jayvijaygohil.nbateamsviewer.testdoubles.ScoreServerGatewayStub
import org.junit.Before
import org.junit.Test

class ScoreServerRepositoryImplTest {
    private lateinit var gateway: ScoreServerGatewayStub
    private lateinit var repository: ScoreServerRepository

    @Before
    fun setUp() {
        gateway = ScoreServerGatewayStub()
        repository = ScoreServerRepositoryImpl(gateway)
    }

    @Test
    fun `test gateway_getAllTeams is invoked` () {
        repository.getAllTeams().test()

        gateway.verifyGetTeamListCalled(1)
        gateway.verifyNoNewInteractions()
    }
}