package dev.jayvijaygohil.nbateamsviewer.domain.usecase

import dev.jayvijaygohil.nbateamsviewer.domain.entity.Team
import dev.jayvijaygohil.nbateamsviewer.domain.usecase.SortTeamsUseCase.SortType
import dev.jayvijaygohil.nbateamsviewer.domain.usecase.SortTeamsUseCase.SortType.*
import io.reactivex.Single

interface SortTeamsUseCase {
    fun execute(list: List<Team>, sortType: SortType): Single<List<Team>>

    enum class SortType {
        NAME_ASC,
        NAME_DESC,
        HIGHEST_WINS,
        LOWEST_WINS,
        HIGHEST_LOSSES,
        LOWEST_LOSSES
    }
}

class SortTeamsUseCaseImpl : SortTeamsUseCase {
    override fun execute(list: List<Team>, sortType: SortType): Single<List<Team>> {
        return Single.just(list)
            .map { it.sortTeams(sortType) }
    }

    private fun List<Team>.sortTeams(sortType: SortType): List<Team> {
        return when (sortType) {
            NAME_ASC -> this.sortedBy { it.teamName }
            NAME_DESC -> this.sortedByDescending { it.teamName }
            LOWEST_WINS -> this.sortedBy { it.teamWins }
            HIGHEST_WINS -> this.sortedByDescending { it.teamWins }
            LOWEST_LOSSES -> this.sortedBy { it.teamLosses }
            HIGHEST_LOSSES -> this.sortedByDescending { it.teamLosses }
        }
    }
}