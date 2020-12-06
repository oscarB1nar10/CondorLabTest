package com.example.condorlabsapp.business.interactors.teams

import com.example.condorlabsapp.business.data.network.ApiResponseHandler
import com.example.condorlabsapp.business.data.network.abstraction.SoccerTeamsNetworkRepository
import com.example.condorlabsapp.business.data.util.safeApiCall
import com.example.condorlabsapp.business.domain.model.SoccerTeam
import com.example.condorlabsapp.business.domain.state.State
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class GetTeamsFromSpanishSoccerLeagueUseCase
@Inject
constructor(
    private val soccerTeamsNetworkDataSource: SoccerTeamsNetworkRepository
) {


    suspend fun getAllTeamsFromSoccerLeague(): State<List<SoccerTeam>> {

        val networkResult = safeApiCall(Dispatchers.IO) {
            soccerTeamsNetworkDataSource.getAllTeamsFromSoccerLeague()
        }

        return object : ApiResponseHandler<List<SoccerTeam>, List<SoccerTeam>>(
            response = networkResult,
            stateEvent = null
        ) {
            override suspend fun handleSuccess(resultObj: List<SoccerTeam>): State<List<SoccerTeam>> {
                return State.success(
                    data = resultObj
                )
            }
        }.getResult()
    }

}