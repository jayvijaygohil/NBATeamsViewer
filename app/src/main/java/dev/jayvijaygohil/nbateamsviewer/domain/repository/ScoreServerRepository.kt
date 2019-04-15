package dev.jayvijaygohil.nbateamsviewer.domain.repository

import dev.jayvijaygohil.nbateamsviewer.domain.data.network.ScoreServerGateway
import dev.jayvijaygohil.nbateamsviewer.domain.entity.Team
import io.reactivex.Single

interface ScoreServerRepository {
    fun getAllTeams(): Single<List<Team>>
}

class ScoreServerRepositoryImpl(
    private val gateway: ScoreServerGateway
) : ScoreServerRepository {
    override fun getAllTeams(): Single<List<Team>> {
        return gateway.getTeamList()
    }
}