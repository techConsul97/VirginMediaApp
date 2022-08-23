package com.sebqv97.virginmediachallenge.repositories

import com.sebqv97.virginmediachallenge.data.local.people.PeopleDao
import com.sebqv97.virginmediachallenge.data.local.people.PeopleEntity
import com.sebqv97.virginmediachallenge.data.local.rooms.RoomsDao
import com.sebqv97.virginmediachallenge.data.local.rooms.RoomsEntity
import com.sebqv97.virginmediachallenge.data.models.people.PeopleResponse
import com.sebqv97.virginmediachallenge.data.models.people.PersonItemModel
import com.sebqv97.virginmediachallenge.data.models.room.RoomItemModel
import com.sebqv97.virginmediachallenge.data.models.room.RoomsResponse
import com.sebqv97.virginmediachallenge.data.remote.apis.ApiConfig.PEOPLE_ENDPOINT
import com.sebqv97.virginmediachallenge.data.remote.apis.ApiConfig.ROOMS_ENDPOINT
import com.sebqv97.virginmediachallenge.data.remote.apis.people.PeopleApi
import com.sebqv97.virginmediachallenge.data.remote.apis.rooms.RoomsApi
import com.sebqv97.virginmediachallenge.util.DataRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class Repository @Inject constructor(
    val roomsDao: RoomsDao,
    val peopleDao: PeopleDao,
    val remoteDataSourceRepository: RemoteDataSourceRepository
):IRepository{



    override suspend fun apiMappper(requestedData: String) =
        when(requestedData){
            ROOMS_ENDPOINT  -> remoteDataSourceRepository.getRoomsFromApi()
            PEOPLE_ENDPOINT -> remoteDataSourceRepository.getEmployeesFromApi()
            else -> throw Throwable("wrong call")
        }

    override fun getAllEmployees(): Flow<List<PeopleEntity>> {
        return peopleDao.readAllPeople()
    }

    override suspend fun insertEmployee(employeeEntity: PeopleEntity) {
       return peopleDao.insertEmployee(employeeEntity)
    }



    override fun getAllRooms(): Flow<List<RoomsEntity>> {
       return roomsDao.readAllRooms()
    }

    override suspend fun insertRoom(roomEntity: RoomsEntity) {
       return roomsDao.insertRoom(roomEntity)
    }



}