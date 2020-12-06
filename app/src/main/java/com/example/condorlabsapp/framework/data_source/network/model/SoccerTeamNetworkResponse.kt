package com.example.condorlabsapp.framework.data_source.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class SoccerTeamNetworkResponse(
    @SerializedName("teams")
    @Expose
    val teams: List<SoccerTeamNetworkEntity>
)
