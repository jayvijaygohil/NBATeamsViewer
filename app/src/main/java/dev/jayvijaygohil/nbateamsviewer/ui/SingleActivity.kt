package dev.jayvijaygohil.nbateamsviewer.ui

import android.os.Bundle
import androidx.fragment.app.transaction
import dev.jayvijaygohil.nbateamsviewer.R
import dev.jayvijaygohil.nbateamsviewer.common.DaggerActivity
import dev.jayvijaygohil.nbateamsviewer.model.Team
import dev.jayvijaygohil.nbateamsviewer.ui.teamlist.TeamListFragment

class SingleActivity : DaggerActivity(), SingleActivityContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single)

        component().injectSingleActivity(this)

        if (savedInstanceState == null) {
            setupTeamListFragment()
        }
    }

    override fun launchTeamListFragment(list: List<Team>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun launchTeamDetailsFragment(team: Team) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun launchSortTeamFragment() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun setupTeamListFragment() {
        supportFragmentManager.transaction(now = false, allowStateLoss = false) {
            add(R.id.fragment_container, TeamListFragment.newInstance(), TeamListFragment.TAG)
            addToBackStack(TeamListFragment.TAG)
        }
    }
}
