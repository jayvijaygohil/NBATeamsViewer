package dev.jayvijaygohil.nbateamsviewer.usecase

import dev.jayvijaygohil.nbateamsviewer.model.Team
import dev.jayvijaygohil.nbateamsviewer.usecase.SortTeamsUseCase.SortType
import dev.jayvijaygohil.nbateamsviewer.usecase.SortTeamsUseCase.SortType.HIGHEST_LOSSES
import dev.jayvijaygohil.nbateamsviewer.usecase.SortTeamsUseCase.SortType.HIGHEST_WINS
import dev.jayvijaygohil.nbateamsviewer.usecase.SortTeamsUseCase.SortType.LOWEST_LOSSES
import dev.jayvijaygohil.nbateamsviewer.usecase.SortTeamsUseCase.SortType.LOWEST_WINS
import dev.jayvijaygohil.nbateamsviewer.usecase.SortTeamsUseCase.SortType.NAME_ASC
import dev.jayvijaygohil.nbateamsviewer.usecase.SortTeamsUseCase.SortType.NAME_DESC
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
            NAME_ASC -> this.sortedBy { it.teamName[0] }
            NAME_DESC -> this.sortedByDescending { it.teamName[0] }
            LOWEST_WINS -> this.sortedBy { it.teamWins }
            HIGHEST_WINS -> this.sortedByDescending { it.teamWins }
            LOWEST_LOSSES -> this.sortedBy { it.teamLosses }
            HIGHEST_LOSSES -> this.sortedByDescending { it.teamLosses }
        }
    }
}