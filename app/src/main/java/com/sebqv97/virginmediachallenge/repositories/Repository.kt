package com.sebqv97.virginmediachallenge.repositories

import com.sebqv97.virginmediachallenge.data.models.people.PersonItemModel
import com.sebqv97.virginmediachallenge.data.models.room.RoomItemModel
import com.sebqv97.virginmediachallenge.data.remote.apis.ApiConfig.PEOPLE_ENDPOINT
import com.sebqv97.virginmediachallenge.data.remote.apis.ApiConfig.ROOMS_ENDPOINT
import javax.inject.Inject


class Repository @Inject constructor(
    val localDataSourceRepository: LocalDataSourceRepository,
    val remoteDataSourceRepository: RemoteDataSourceRepository
){

    val localAccess = localDataSourceRepository
    val remoteAccess = remoteDataSourceRepository

    suspend fun apiMapper(requestedData:String) =
        when(requestedData){
            ROOMS_ENDPOINT  -> remoteAccess.getRoomsFromApi()
            PEOPLE_ENDPOINT -> remoteAccess.getEmployeesFromApi()
            else -> throw Throwable("wrong call")
        }


}