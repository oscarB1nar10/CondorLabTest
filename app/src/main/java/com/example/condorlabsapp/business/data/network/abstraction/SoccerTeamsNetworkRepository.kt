package com.example.condorlabsapp.business.data.network.abstraction

import com.example.condorlabsapp.business.domain.model.SoccerTeam

interface SoccerTeamsNetworkRepository {
    suspend fun getAllTeamsFromSoccerLeague(): List<SoccerTeam>
}