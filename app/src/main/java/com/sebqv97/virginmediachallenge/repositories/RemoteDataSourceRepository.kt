package com.sebqv97.virginmediachallenge.repositories

import com.sebqv97.virginmediachallenge.data.models.people.PeopleResponse
import com.sebqv97.virginmediachallenge.data.models.room.RoomsResponse
import com.sebqv97.virginmediachallenge.data.remote.apis.people.PeopleApi
import com.sebqv97.virginmediachallenge.data.remote.apis.rooms.RoomsApi
import com.sebqv97.virginmediachallenge.util.DataRequest
import java.lang.Exception
import javax.inject.Inject

class RemoteDataSourceRepository @Inject constructor(
    val peopleApi: PeopleApi,
    val roomsApi: RoomsApi
)  : IRemoteDataSourceRepository {
    override suspend fun getEmployeesFromApi(): DataRequest<PeopleResponse>{
        return  try {
            val response = peopleApi.getPeople()
            val result = response.body()
            if(response.isSuccessful && result != null){
                DataRequest.Success(result)
            }
            else{
                DataRequest.Failed(response.message())
            }

        }catch (e: Exception){
            DataRequest.Failed(e.message ?: "Unexpected Error")
        }
    }

    override suspend fun getRoomsFromApi(): DataRequest<RoomsResponse> {
        return  try {
            val response = roomsApi.getRooms()
            val result = response.body()
            if(response.isSuccessful && result != null){
                DataRequest.Success(result)
            }
            else{
                DataRequest.Failed(response.message())
            }

        }catch (e: Exception){
            DataRequest.Failed(e.message ?: "Unexpected Error")
        }
    }
    }






