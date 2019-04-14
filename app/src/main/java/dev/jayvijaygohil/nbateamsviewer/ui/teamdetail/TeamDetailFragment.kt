package dev.jayvijaygohil.nbateamsviewer.ui.teamdetail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomappbar.BottomAppBar.FAB_ALIGNMENT_MODE_END
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dev.jayvijaygohil.nbateamsviewer.R
import dev.jayvijaygohil.nbateamsviewer.common.DaggerFragment
import dev.jayvijaygohil.nbateamsviewer.model.Team
import dev.jayvijaygohil.nbateamsviewer.ui.SingleActivity
import kotlinx.android.synthetic.main.fragment_team_detail.rv_player_list
import kotlinx.android.synthetic.main.fragment_team_detail.tv_team_losses
import kotlinx.android.synthetic.main.fragment_team_detail.tv_team_name
import kotlinx.android.synthetic.main.fragment_team_detail.tv_team_wins
import javax.inject.Inject

class TeamDetailFragment : DaggerFragment() {
    @Inject
    lateinit var adapter: TeamDetailAdapter

    @Inject
    lateinit var activityContext: Context

    override fun onAttach(context: Context) {
        component().injectTeamDetailFragment(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_team_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupBackFab()
        setupRecyclerView()
        showTeamPlayers()
    }

    private fun setupRecyclerView() {
        rv_player_list.layoutManager = LinearLayoutManager(context)
        rv_player_list.adapter = adapter
    }

    private fun setupBackFab() {
        getBottomAppBar().also {
            it.fabAlignmentMode = FAB_ALIGNMENT_MODE_END
        }

        getFab().also {
            it.setImageResource(R.drawable.ic_back)
            it.setOnClickListener {
                fragmentManager!!.popBackStack()
            }
        }
    }

    private fun showTeamPlayers() {
        arguments?.getParcelable<Team>(TAG)?.let { team ->
            tv_team_name.text = team.teamName
            tv_team_wins.text = team.teamWins.toString()
            tv_team_losses.text = team.teamLosses.toString()
            adapter.submitNewPlayerList(team.teamPlayers)
        }
    }

    private fun getFab(): FloatingActionButton {
        return (activityContext as SingleActivity).findViewById(R.id.fab_bottom_app_bar)
    }

    private fun getBottomAppBar(): BottomAppBar {
        return (activityContext as SingleActivity).findViewById(R.id.bottom_app_bar)
    }

    companion object {
        const val TAG = "[TeamDetailFragment]"

        fun newInstance(team: Team): TeamDetailFragment {
            val bundle = Bundle().also { it.putParcelable(TAG, team) }

            return TeamDetailFragment().also {
                it.arguments = bundle
            }
        }
    }
}