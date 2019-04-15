package dev.jayvijaygohil.nbateamsviewer.ui.teamlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import dev.jayvijaygohil.nbateamsviewer.R
import dev.jayvijaygohil.nbateamsviewer.domain.entity.Team
import dev.jayvijaygohil.nbateamsviewer.ui.SingleActivityContract
import kotlinx.android.synthetic.main.rv_team_item.view.*

class TeamListAdapter(private val singleActivity: SingleActivityContract.View) :
    RecyclerView.Adapter<TeamViewHolder>() {
    private val teamDiffCallback: DiffUtil.ItemCallback<Team>
    private val asyncTeamListDiff: AsyncListDiffer<Team>

    init {
        teamDiffCallback = object : DiffUtil.ItemCallback<Team>() {
            override fun areItemsTheSame(oldItem: Team, newItem: Team): Boolean {
                return oldItem.teamId == newItem.teamId
            }

            override fun areContentsTheSame(oldItem: Team, newItem: Team): Boolean {
                if (oldItem.teamName != newItem.teamName) return false
                if (oldItem.teamWins != newItem.teamWins) return false
                if (oldItem.teamLosses != newItem.teamLosses) return false

                return true
            }
        }

        asyncTeamListDiff = AsyncListDiffer(this, teamDiffCallback).also { it.submitList(emptyList()) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.rv_team_item, parent, false)

        return TeamViewHolder(singleActivity, view)
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bindTeam(getTeamFromList(position))
    }

    override fun getItemCount(): Int {
        return getTeamList().size
    }

    fun addNewTeamList(list: List<Team>) {
        asyncTeamListDiff.submitList(list)
    }

    fun clear() {
        asyncTeamListDiff.submitList(emptyList())
    }

    fun getTeamList(): List<Team> {
        return asyncTeamListDiff.currentList
    }

    private fun getTeamFromList(position: Int): Team {
        return asyncTeamListDiff.currentList[position]
    }
}

class TeamViewHolder(private val singleActivity: SingleActivityContract.View, view: View) : RecyclerView.ViewHolder(
    view
), View.OnClickListener {
    private lateinit var team: Team

    init {
        view.setOnClickListener(this)
    }

    fun bindTeam(team: Team) {
        this.team = team

        itemView.tv_team_name.text = team.teamName
        itemView.tv_team_wins.text = team.teamWins.toString()
        itemView.tv_team_losses.text = team.teamLosses.toString()
    }

    override fun onClick(v: View?) {
        singleActivity.launchTeamDetailsFragment(team)
    }
}