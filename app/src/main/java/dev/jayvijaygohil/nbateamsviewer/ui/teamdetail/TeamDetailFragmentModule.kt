package dev.jayvijaygohil.nbateamsviewer.ui.teamdetail

import dagger.Module
import dagger.Provides
import dev.jayvijaygohil.nbateamsviewer.ui.teamdetail.TeamDetailAdapter

@Module
class TeamDetailFragmentModule {
    @Provides
    fun provideTeamDetailAdapter(): TeamDetailAdapter {
        return TeamDetailAdapter()
    }
}