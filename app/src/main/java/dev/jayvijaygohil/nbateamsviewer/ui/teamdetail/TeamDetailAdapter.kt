package dev.jayvijaygohil.nbateamsviewer.ui.teamdetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.jayvijaygohil.nbateamsviewer.R
import dev.jayvijaygohil.nbateamsviewer.domain.entity.Players
import kotlinx.android.synthetic.main.rv_player_item.view.*

class TeamDetailAdapter : RecyclerView.Adapter<PlayerViewHolder>() {
    private var playerList = emptyList<Players>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.rv_player_item, parent, false)

        return PlayerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return playerList.size
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bindTeam(playerList[position])
    }

    fun submitNewPlayerList(newPlayerList: List<Players>) {
        playerList = newPlayerList
    }
}

class PlayerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bindTeam(player: Players) {
        val playerFullName = "${player.playerFirstName.trim()} ${player.playerLastName.trim()}"

        itemView.tv_player_name.text = playerFullName
        itemView.tv_player_number.text = player.playerNumber.toString()
        itemView.tv_player_position.text = player.playerPosition
    }
}