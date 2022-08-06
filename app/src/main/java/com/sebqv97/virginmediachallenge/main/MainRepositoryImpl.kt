package com.sebqv97.virginmediachallenge.main

import com.sebqv97.virginmediachallenge.data.apis.ApiConfig.PEOPLE_ENDPOINT
import com.sebqv97.virginmediachallenge.data.apis.ApiConfig.ROOMS_ENDPOINT
import com.sebqv97.virginmediachallenge.data.apis.people.PeopleApi
import com.sebqv97.virginmediachallenge.data.apis.rooms.RoomsApi
import com.sebqv97.virginmediachallenge.data.models.people.PeopleResponse
import com.sebqv97.virginmediachallenge.data.models.room.RoomsResponse
import com.sebqv97.virginmediachallenge.util.DataRequest
import java.lang.Exception
import javax.inject.Inject


class MainRepositoryImpl @Inject constructor(
    private val peopleApi: PeopleApi
    ,private val roomsApi: RoomsApi
    ) : MainRepository {
    override suspend fun getPeople(): DataRequest<PeopleResponse> {

      return  try {
            val response = peopleApi.getPeople()
            val result = response.body()
            if(response.isSuccessful && result != null){
                DataRequest.Success(result)
            }
            else{
                DataRequest.Failed(response.message())
            }

        }catch (e:Exception){
            DataRequest.Failed(e.message ?: "Unexpected Error")
        }



    }

    override suspend fun getRooms(): DataRequest<RoomsResponse> {
        return try {
            val response = roomsApi.getRooms()
            val result = response.body()
            if(response.isSuccessful && result != null){
                DataRequest.Success(result)
            }
            else{
                DataRequest.Failed(response.message() )
            }
        }catch (e:Exception){
            DataRequest.Failed(e.message ?: "Unexpected behaviour")
        }
    }

    suspend fun apiMapper(requestedData:String) =
        when(requestedData){
            ROOMS_ENDPOINT  -> getRooms()
            PEOPLE_ENDPOINT -> getPeople()
            else -> throw Throwable("wrong call")
        }

}