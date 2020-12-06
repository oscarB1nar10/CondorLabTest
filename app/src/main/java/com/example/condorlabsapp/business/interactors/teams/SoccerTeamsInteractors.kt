package com.example.condorlabsapp.business.interactors.teams

import javax.inject.Inject

// Use cases
class SoccerTeamsInteractors
@Inject constructor(
    val getTeamsFromSpanishSoccerLeague: GetTeamsFromSpanishSoccerLeagueUseCase
)