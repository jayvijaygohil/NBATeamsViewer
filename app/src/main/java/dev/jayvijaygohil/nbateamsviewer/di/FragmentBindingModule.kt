package dev.jayvijaygohil.nbateamsviewer.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dev.jayvijaygohil.nbateamsviewer.ui.teamdetail.TeamDetailFragment
import dev.jayvijaygohil.nbateamsviewer.ui.teamdetail.TeamDetailFragmentModule
import dev.jayvijaygohil.nbateamsviewer.ui.teamlist.TeamListFragment
import dev.jayvijaygohil.nbateamsviewer.ui.teamlist.TeamListFragmentModule

@Module
abstract class FragmentBindingModule {
    @ContributesAndroidInjector(modules = [TeamListFragmentModule::class])
    abstract fun contributeTeaListFragment(): TeamListFragment

    @ContributesAndroidInjector(modules = [TeamDetailFragmentModule::class])
    abstract fun contributeTeamDetailFragment(): TeamDetailFragment
}