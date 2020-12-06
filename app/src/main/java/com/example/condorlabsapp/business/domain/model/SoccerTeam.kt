package com.example.condorlabsapp.business.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class SoccerTeam(
    val teamId: Int,
    val teamName: String,
    val teamDescription: String,
    val temFoundationYear: Int,
    val temBadge: String? = null,
    val teamJersey: String? = null,
    val teamWebSide: String? = null

) : Parcelable {


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SoccerTeam

        if (teamId != other.teamId) return false
        if (teamName != other.teamName) return false
        if (teamDescription != other.teamDescription) return false
        if (temFoundationYear != other.temFoundationYear) return false

        return true
    }

}