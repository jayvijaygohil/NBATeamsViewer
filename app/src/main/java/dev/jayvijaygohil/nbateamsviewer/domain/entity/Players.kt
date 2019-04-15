package dev.jayvijaygohil.nbateamsviewer.domain.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Players(
    @field:SerializedName("id")
    val playerId: Int = 0,

    @field:SerializedName("number")
    val playerNumber: Int = 0,

    @field:SerializedName("first_name")
    val playerFirstName: String = "",

    @field:SerializedName("last_name")
    val playerLastName: String = "",

    @field:SerializedName("position")
    val playerPosition: String = ""
) : Parcelable