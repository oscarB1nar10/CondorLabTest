package com.example.condorlabsapp.framework.presentation.dashboard

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.condorlabsapp.business.domain.model.SoccerTeam
import com.example.condorlabsapp.business.domain.state.State
import com.example.condorlabsapp.business.interactors.teams.SoccerTeamsInteractors


class DashboardFragmentViewModel
@ViewModelInject constructor(
    private val soccerTeamsInteractors: SoccerTeamsInteractors
) : ViewModel() {

    private val mutableSoccerTeams: MutableLiveData<Boolean> = MutableLiveData()

    val soccerTeams: LiveData<State<List<SoccerTeam>>> =
        Transformations.switchMap(mutableSoccerTeams) {
            liveData(context = viewModelScope.coroutineContext) {
                emit(State.Loading())
                emit(soccerTeamsInteractors.getTeamsFromSpanishSoccerLeague.getAllTeamsFromSoccerLeague())
            }
        }

    fun getSoccerTeams() {
        if (mutableSoccerTeams.value != true)
            mutableSoccerTeams.value = true
    }

}