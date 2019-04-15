package dev.jayvijaygohil.nbateamsviewer.ui.teamdetail

import dagger.Module
import dagger.Provides

@Module
class TeamDetailFragmentModule {
    @Provides
    fun provideTeamDetailAdapter(): TeamDetailAdapter {
        return TeamDetailAdapter()
    }
}