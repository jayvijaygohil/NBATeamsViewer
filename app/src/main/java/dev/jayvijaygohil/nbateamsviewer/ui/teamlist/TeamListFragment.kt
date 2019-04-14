package dev.jayvijaygohil.nbateamsviewer.ui.teamlist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomappbar.BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dev.jayvijaygohil.nbateamsviewer.R
import dev.jayvijaygohil.nbateamsviewer.common.DaggerFragment
import dev.jayvijaygohil.nbateamsviewer.model.Team
import dev.jayvijaygohil.nbateamsviewer.ui.SingleActivity
import dev.jayvijaygohil.nbateamsviewer.ui.SingleActivityContract
import dev.jayvijaygohil.nbateamsviewer.ui.teamsort.TeamSortDialogFragment.Companion.SORT_RESULT_INTENT_KEY
import dev.jayvijaygohil.nbateamsviewer.ui.teamsort.TeamSortDialogFragment.Companion.SORT_RESULT_CODE
import dev.jayvijaygohil.nbateamsviewer.usecase.SortTeamsUseCase.SortType
import kotlinx.android.synthetic.main.fragment_team_list.*
import javax.inject.Inject

class TeamListFragment : DaggerFragment(), TeamListContract.View {
    @Inject
    @JvmField
    var singleActivityViewContract: SingleActivityContract.View? = null

    @Inject
    lateinit var activityContext: Context

    @Inject
    lateinit var presenter: TeamListContract.Presenter

    @Inject
    lateinit var adapter: TeamListAdapter

    override fun onAttach(context: Context) {
        component().injectTeamListFragment(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_team_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupPresenter()
        setupFilterFab()
        setupRecyclerView()
        setupSwipeToRefresh()
        setupRetry()

        if (savedInstanceState == null) {
            presenter.fetchTeams()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == SORT_REQUEST_CODE && resultCode == SORT_RESULT_CODE && data != null) {
            val sortTypeOrdinal = data.getIntExtra(SORT_RESULT_INTENT_KEY, 0)
            val sortType = SortType.values()[sortTypeOrdinal]
            presenter.sortTeams(adapter.getTeamList(), sortType)
        }
    }

    override fun showTeams(list: List<Team>) {
        getFab().also { it.show() }
        rv_swipe_refresh_container.isRefreshing = false
        rv_all_team_list.visibility = View.VISIBLE
        error_container.visibility = View.GONE
        tv_error_message.visibility = View.GONE
        btn_error_retry.visibility = View.GONE

        adapter.addNewTeamList(list)
    }

    override fun showError() {
        getFab().also { it.hide() }
        rv_swipe_refresh_container.isRefreshing = false
        rv_all_team_list.visibility = View.GONE
        error_container.visibility = View.VISIBLE
        tv_error_message.visibility = View.VISIBLE
        btn_error_retry.visibility = View.VISIBLE

        adapter.clear()
    }

    private fun setupPresenter() {
        presenter.attachView(this)
    }

    private fun setupRecyclerView() {
        rv_all_team_list.layoutManager = LinearLayoutManager(context)
        rv_all_team_list.adapter = adapter
    }

    private fun setupSwipeToRefresh() {
        rv_swipe_refresh_container.setOnRefreshListener {
            rv_swipe_refresh_container.isRefreshing = true
            presenter.fetchTeams()
        }
    }

    private fun setupFilterFab() {
        getBottomAppBar().also {
            it.fabAlignmentMode = FAB_ALIGNMENT_MODE_CENTER
        }

        getFab().also {
            it.setImageResource(R.drawable.ic_filter)
            it.setOnClickListener {
                singleActivityViewContract?.launchSortTeamFragment(TAG, SORT_REQUEST_CODE)
            }
        }
    }

    private fun setupRetry() {
        btn_error_retry.setOnClickListener {
            presenter.fetchTeams()
        }
    }

    private fun getFab(): FloatingActionButton {
        return (activityContext as SingleActivity).findViewById(R.id.fab_bottom_app_bar)
    }

    private fun getBottomAppBar(): BottomAppBar {
        return (activityContext as SingleActivity).findViewById(R.id.bottom_app_bar)
    }

    override fun onDetach() {
        super.onDetach()
        singleActivityViewContract = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = TeamListFragment()

        const val SORT_REQUEST_CODE = 5000
        const val TAG = "[TeamListFragment]"
    }
}
