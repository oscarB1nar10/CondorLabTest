package com.example.condorlabsapp.business.data.network.abstraction

import com.example.condorlabsapp.framework.data_source.network.model.SoccerTeamNetworkResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TheSportDbApi {

    @GET("api/v1/json/1/search_all_teams.php")
    suspend fun getAllTeamsFromSoccerLeague(
        @Query("s") sport: String = "Soccer",
        @Query("c") league: String
    ): SoccerTeamNetworkResponse
}