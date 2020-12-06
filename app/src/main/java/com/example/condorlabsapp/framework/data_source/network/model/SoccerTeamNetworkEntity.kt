package com.example.condorlabsapp.framework.data_source.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SoccerTeamNetworkEntity(

    @SerializedName("idTeam")
    @Expose
    val teamId: Int,

    @SerializedName("strTeam")
    @Expose
    val teamName: String,

    @SerializedName("strDescriptionEN")
    @Expose
    val teamDescription: String,

    @SerializedName("intFormedYear")
    @Expose
    val temFoundationYear: Int,

    @SerializedName("strTeamBadge")
    @Expose
    val temBadge: String? = null,

    @SerializedName("strTeamJersey")
    @Expose
    val teamJersey: String? = null,

    @SerializedName("strWebsite")
    @Expose
    val teamWebSide: String?
)
