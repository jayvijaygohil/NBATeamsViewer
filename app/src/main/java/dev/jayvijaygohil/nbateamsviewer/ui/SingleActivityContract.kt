package dev.jayvijaygohil.nbateamsviewer.ui

import dev.jayvijaygohil.nbateamsviewer.model.Team

interface SingleActivityContract {
    interface View {
        fun launchTeamListFragment()
        fun launchTeamDetailsFragment(team: Team)
        fun launchSortTeamFragment(requestingFragmentTag: String, requestCode: Int)
    }
}