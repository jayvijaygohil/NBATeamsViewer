package dev.jayvijaygohil.nbateamsviewer.ui

import dev.jayvijaygohil.nbateamsviewer.domain.entity.Team

interface SingleActivityContract {
    interface View {
        fun launchTeamListFragment()
        fun launchTeamDetailsFragment(team: Team)
        fun launchSortTeamFragment(requestingFragmentTag: String, requestCode: Int)
    }
}