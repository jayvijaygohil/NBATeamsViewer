package dev.jayvijaygohil.nbateamsviewer.ui.teamlist

import dev.jayvijaygohil.nbateamsviewer.common.BasePresenter
import dev.jayvijaygohil.nbateamsviewer.domain.entity.Team
import dev.jayvijaygohil.nbateamsviewer.domain.usecase.SortTeamsUseCase.SortType

interface TeamListContract {
    interface View {
        fun showTeams(list: List<Team>)
        fun showError()
    }

    interface Presenter : BasePresenter<View> {
        fun fetchTeams()
        fun sortTeams(list: List<Team>, newSortType: SortType)
    }
}