package com.sebqv97.virginmediachallenge.repositories

import androidx.lifecycle.LiveData
import com.sebqv97.virginmediachallenge.data.local.people.PeopleEntity
import com.sebqv97.virginmediachallenge.data.local.rooms.RoomsEntity
import com.sebqv97.virginmediachallenge.data.models.people.PeopleResponse
import com.sebqv97.virginmediachallenge.data.models.people.PersonItemModel
import com.sebqv97.virginmediachallenge.data.models.room.RoomItemModel
import com.sebqv97.virginmediachallenge.data.models.room.RoomsResponse
import kotlinx.coroutines.flow.Flow

interface ILocalDataSourceRepository {

     fun getAllEmployees(): Flow<List<PeopleEntity>>
    suspend fun insertEmployee(employeeEntity:PeopleEntity)


     fun getAllRooms() : Flow<List<RoomsEntity>>
    suspend fun insertRoom(roomEntity:RoomsEntity)


}