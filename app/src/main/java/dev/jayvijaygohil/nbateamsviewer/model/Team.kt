package dev.jayvijaygohil.nbateamsviewer.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Team(
    @field:SerializedName("id")
    val teamId: Int,

    @field:SerializedName("full_name")
    val teamName: String,

    @field:SerializedName("wins")
    val teamWins: Int,

    @field:SerializedName("losses")
    val teamLosses: Int,

    @field:SerializedName("players")
    val teamPlayers: List<Players>
) : Parcelable