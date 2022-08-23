package com.sebqv97.virginmediachallenge.repositories

import com.sebqv97.virginmediachallenge.data.local.people.PeopleEntity
import com.sebqv97.virginmediachallenge.data.local.rooms.RoomsEntity
import com.sebqv97.virginmediachallenge.data.models.people.PeopleResponse
import com.sebqv97.virginmediachallenge.data.models.room.RoomsResponse
import com.sebqv97.virginmediachallenge.util.DataRequest
import kotlinx.coroutines.flow.Flow

interface IRepository{


    fun getAllEmployees(): Flow<List<PeopleEntity>>
    suspend fun insertEmployee(employeeEntity: PeopleEntity)

    suspend fun apiMappper(requestedData:String):DataRequest<*>


    fun getAllRooms() : Flow<List<RoomsEntity>>
    suspend fun insertRoom(roomEntity: RoomsEntity)

}