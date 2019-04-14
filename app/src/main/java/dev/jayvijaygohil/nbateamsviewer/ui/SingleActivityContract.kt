package dev.jayvijaygohil.nbateamsviewer.ui

import dev.jayvijaygohil.nbateamsviewer.model.Team

interface SingleActivityContract {
    interface View {
        fun launchTeamListFragment(list: List<Team>)
        fun launchTeamDetailsFragment(team: Team)
        fun launchSortTeamFragment()
    }
}