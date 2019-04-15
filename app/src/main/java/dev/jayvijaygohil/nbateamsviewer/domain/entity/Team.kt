package dev.jayvijaygohil.nbateamsviewer.domain.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Team(
    @field:SerializedName("id")
    val teamId: Int = 0,

    @field:SerializedName("full_name")
    val teamName: String = "",

    @field:SerializedName("wins")
    val teamWins: Int = 0,

    @field:SerializedName("losses")
    val teamLosses: Int = 0,

    @field:SerializedName("players")
    val teamPlayers: List<Players> = emptyList()
) : Parcelable