package dev.jayvijaygohil.nbateamsviewer.usecase

import dev.jayvijaygohil.nbateamsviewer.model.Team
import dev.jayvijaygohil.nbateamsviewer.usecase.SortTeamsListUseCase.SortType
import dev.jayvijaygohil.nbateamsviewer.usecase.SortTeamsListUseCase.SortType.*
import io.reactivex.Single

interface SortTeamsListUseCase {
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

class SortTeamsUseCaseImpl : SortTeamsListUseCase {
    override fun execute(list: List<Team>, sortType: SortType): Single<List<Team>> {
        return Single.just(list)
            .map { it.sortTeams(sortType) }
    }

    private fun List<Team>.sortTeams(sortType: SortType): List<Team> {
        return when (sortType) {
            NAME_ASC -> this.sortedBy { it.teamName[0] }
            NAME_DESC -> this.sortedByDescending { it.teamName[0] }
            LOWEST_WINS -> this.sortedBy { it.teamWins }
            HIGHEST_WINS -> this.sortedByDescending { it.teamWins }
            LOWEST_LOSSES -> this.sortedBy { it.teamLosses }
            HIGHEST_LOSSES -> this.sortedByDescending { it.teamLosses }
        }
    }
}