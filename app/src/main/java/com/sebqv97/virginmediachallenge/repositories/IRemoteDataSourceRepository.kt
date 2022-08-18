package com.sebqv97.virginmediachallenge.repositories

import com.sebqv97.virginmediachallenge.data.models.people.PeopleResponse
import com.sebqv97.virginmediachallenge.data.models.room.RoomsResponse
import com.sebqv97.virginmediachallenge.util.DataRequest

interface IRemoteDataSourceRepository {
    suspend fun getEmployeesFromApi() : DataRequest<PeopleResponse>
    suspend fun getRoomsFromApi() : DataRequest<RoomsResponse>

}