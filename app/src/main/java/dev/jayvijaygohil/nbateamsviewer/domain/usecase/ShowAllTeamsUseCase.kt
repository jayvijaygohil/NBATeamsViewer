package dev.jayvijaygohil.nbateamsviewer.domain.usecase

import dev.jayvijaygohil.nbateamsviewer.domain.entity.Team
import dev.jayvijaygohil.nbateamsviewer.domain.repository.ScoreServerRepository
import io.reactivex.Single

interface ShowAllTeamsUseCase {
    fun execute(): Single<List<Team>>
}

class ShowAllTeamsUseCaseImpl(
    private val repository: ScoreServerRepository
) : ShowAllTeamsUseCase {
    override fun execute(): Single<List<Team>> {
        return repository
            .getAllTeams()
            .map { it.sortTeams().sortPlayers() }
    }

    private fun List<Team>.sortTeams(): List<Team> {
        return this.sortedBy { it.teamName }
    }

    private fun List<Team>.sortPlayers(): List<Team> {
        val newList = mutableListOf<Team>()

        this.forEach { team ->
            val sortedPlayers = team.teamPlayers.sortedBy { it.playerFirstName }
            val newTeam = team.copy(teamPlayers = sortedPlayers)
            newList.add(newTeam)
        }

        return newList
    }
}