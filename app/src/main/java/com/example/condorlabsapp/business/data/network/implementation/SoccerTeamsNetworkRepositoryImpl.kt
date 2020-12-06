package com.example.condorlabsapp.business.data.network.implementation

import com.example.condorlabsapp.business.data.network.abstraction.SoccerTeamsNetworkRepository
import com.example.condorlabsapp.business.data.network.abstraction.TheSportDbApi
import com.example.condorlabsapp.business.domain.model.SoccerTeam
import com.example.condorlabsapp.framework.data_source.network.mapers.SoccerTeamMapper
import javax.inject.Inject

class SoccerTeamsNetworkRepositoryImpl
@Inject
constructor(
    val theSportDbApi: TheSportDbApi,
    val soccerTeamMapper: SoccerTeamMapper
) : SoccerTeamsNetworkRepository {

    override suspend fun getAllTeamsFromSoccerLeague(): List<SoccerTeam> {

        return  soccerTeamMapper.entityListToSoccerTeamList(
            theSportDbApi.getAllTeamsFromSoccerLeague("Soccer","Spain")
                .teams
        )

    }
}