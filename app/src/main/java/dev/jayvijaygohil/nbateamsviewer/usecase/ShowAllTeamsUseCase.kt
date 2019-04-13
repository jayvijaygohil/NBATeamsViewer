package dev.jayvijaygohil.nbateamsviewer.usecase

import dev.jayvijaygohil.nbateamsviewer.data.network.ScoreServerRepository
import dev.jayvijaygohil.nbateamsviewer.model.Team
import io.reactivex.Single
import javax.inject.Inject

interface ShowAllTeamsUseCase {
    fun execute(): Single<List<Team>>
}

class ShowAllTeamsUseCaseImpl @Inject constructor(
    private val repository: ScoreServerRepository
) : ShowAllTeamsUseCase {
    override fun execute(): Single<List<Team>> {
        return repository
            .getAllTeams()
            .map { it.sortTeams().sortPlayers() }
    }

    private fun List<Team>.sortTeams(): List<Team> {
        return this.sortedBy { it.teamName[0] }
    }

    private fun List<Team>.sortPlayers(): List<Team> {
        val newList = mutableListOf<Team>()

        this.forEach { team ->
            val sortedPlayers = team.teamPlayers.sortedBy { it.playerFirstName[0] }
            val newTeam = team.copy(teamPlayers = sortedPlayers)
            newList.add(newTeam)
        }

        return newList
    }
}