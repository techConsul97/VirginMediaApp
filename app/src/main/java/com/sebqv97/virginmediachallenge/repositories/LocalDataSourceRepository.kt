package com.sebqv97.virginmediachallenge.repositories

import androidx.lifecycle.LiveData
import com.sebqv97.virginmediachallenge.data.local.people.PeopleDao
import com.sebqv97.virginmediachallenge.data.local.people.PeopleEntity
import com.sebqv97.virginmediachallenge.data.local.rooms.RoomsDao
import com.sebqv97.virginmediachallenge.data.local.rooms.RoomsEntity
import com.sebqv97.virginmediachallenge.data.models.people.PeopleResponse
import com.sebqv97.virginmediachallenge.data.models.people.PersonItemModel
import com.sebqv97.virginmediachallenge.data.models.room.RoomItemModel
import com.sebqv97.virginmediachallenge.data.models.room.RoomsResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceRepository @Inject constructor(
    private val peopleDao: PeopleDao,
    private val roomsDao: RoomsDao
) : ILocalDataSourceRepository {
    override  fun getAllEmployees() = peopleDao.readAllPeople()
    override suspend fun insertEmployee(employeeEntity: PeopleEntity) = peopleDao.insertEmployee(employeeEntity)



    override  fun getAllRooms() = roomsDao.readAllRooms()
    override suspend fun insertRoom(roomEntity: RoomsEntity) = roomsDao.insertRoom(roomEntity)


}