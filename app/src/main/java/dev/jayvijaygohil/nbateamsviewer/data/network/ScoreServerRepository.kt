package dev.jayvijaygohil.nbateamsviewer.data.network

import dev.jayvijaygohil.nbateamsviewer.model.Team
import io.reactivex.Single

interface ScoreServerRepository {
    fun getAllTeams(): Single<List<Team>>
}

class ScoreServerRepositoryImpl (
    private val gateway: ScoreServerGateway
): ScoreServerRepository {
    override fun getAllTeams(): Single<List<Team>> {
        return gateway.getTeamList()
    }
}