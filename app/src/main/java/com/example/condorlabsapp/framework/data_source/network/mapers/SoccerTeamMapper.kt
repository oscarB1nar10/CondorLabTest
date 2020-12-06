package com.example.condorlabsapp.framework.data_source.network.mapers

import com.example.condorlabsapp.business.domain.model.SoccerTeam
import com.example.condorlabsapp.business.domain.util.EntityMapper
import com.example.condorlabsapp.framework.data_source.network.model.SoccerTeamNetworkEntity
import javax.inject.Inject

class SoccerTeamMapper
@Inject
constructor() : EntityMapper<SoccerTeamNetworkEntity, SoccerTeam> {


    fun entityListToSoccerTeamList(entities: List<SoccerTeamNetworkEntity>): List<SoccerTeam> {
        val list: ArrayList<SoccerTeam> = ArrayList()
        for (entity in entities) {
            list.add(mapFromEntity(entity))
        }
        return list
    }

    fun SoccerTeamListToEntityList(notes: List<SoccerTeam>): List<SoccerTeamNetworkEntity> {
        val entities: ArrayList<SoccerTeamNetworkEntity> = ArrayList()
        for (note in notes) {
            entities.add(mapToEntity(note))
        }
        return entities
    }

    override fun mapFromEntity(entity: SoccerTeamNetworkEntity): SoccerTeam {
        return SoccerTeam(
            teamId = entity.teamId,
            teamName = entity.teamName,
            teamDescription = entity.teamDescription,
            temFoundationYear = entity.temFoundationYear,
            temBadge = entity.temBadge,
            teamJersey = entity.teamJersey,
            teamWebSide = entity.teamWebSide
        )
    }

    override fun mapToEntity(domainModel: SoccerTeam): SoccerTeamNetworkEntity {
        return SoccerTeamNetworkEntity(
            teamId = domainModel.teamId,
            teamName = domainModel.teamName,
            teamDescription = domainModel.teamDescription,
            temFoundationYear = domainModel.temFoundationYear,
            temBadge = domainModel.temBadge,
            teamJersey = domainModel.teamJersey,
            teamWebSide = domainModel.teamWebSide
        )
    }
}