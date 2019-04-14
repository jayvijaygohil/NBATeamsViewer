package dev.jayvijaygohil.nbateamsviewer.ui.di

import dagger.Subcomponent
import dev.jayvijaygohil.nbateamsviewer.common.SingleActivityScope
import dev.jayvijaygohil.nbateamsviewer.ui.SingleActivity
import dev.jayvijaygohil.nbateamsviewer.ui.teamlist.TeamListFragment

@SingleActivityScope
@Subcomponent(modules = [PresentationModule::class])
interface PresentationComponent {
    fun injectSingleActivity(activity: SingleActivity)
    fun injectTeamListFragment(fragment: TeamListFragment)
}