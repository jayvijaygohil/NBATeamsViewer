package dev.jayvijaygohil.nbateamsviewer.ui

import android.os.Bundle
import androidx.fragment.app.transaction
import dagger.android.support.DaggerAppCompatActivity
import dev.jayvijaygohil.nbateamsviewer.R
import dev.jayvijaygohil.nbateamsviewer.domain.entity.Team
import dev.jayvijaygohil.nbateamsviewer.ui.teamdetail.TeamDetailFragment
import dev.jayvijaygohil.nbateamsviewer.ui.teamlist.TeamListFragment
import dev.jayvijaygohil.nbateamsviewer.ui.teamsort.TeamSortDialogFragment


class SingleActivity : DaggerAppCompatActivity(), SingleActivityContract.View{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single)
        if (savedInstanceState == null) {
            launchTeamListFragment()
        }
    }

    override fun launchTeamListFragment() {
        supportFragmentManager.transaction(now = false, allowStateLoss = false) {
            replace(R.id.fragment_container, TeamListFragment.newInstance(), TeamListFragment.TAG)
            addToBackStack(TeamListFragment.TAG)
        }
    }

    override fun launchTeamDetailsFragment(team: Team) {
        supportFragmentManager.transaction(now = false, allowStateLoss = false) {
            replace(R.id.fragment_container, TeamDetailFragment.newInstance(team), TeamDetailFragment.TAG)
            addToBackStack(TeamDetailFragment.TAG)
        }
    }

    override fun launchSortTeamFragment(requestingFragmentTag: String, requestCode: Int) {
        supportFragmentManager.findFragmentByTag(requestingFragmentTag)?.let { requestingFragment ->
            TeamSortDialogFragment.newInstance().also { sortDialogFragment ->
                sortDialogFragment.setTargetFragment(requestingFragment, requestCode)
                sortDialogFragment.show(supportFragmentManager, TeamSortDialogFragment.TAG)
            }
        }
    }
}
