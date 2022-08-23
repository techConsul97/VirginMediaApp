package com.sebqv97.virginmediachallenge.repositories

import android.provider.ContactsContract
import com.sebqv97.virginmediachallenge.data.models.people.PeopleResponse
import com.sebqv97.virginmediachallenge.data.models.room.RoomsResponse
import com.sebqv97.virginmediachallenge.data.remote.apis.people.PeopleApi
import com.sebqv97.virginmediachallenge.data.remote.apis.rooms.RoomsApi
import com.sebqv97.virginmediachallenge.util.DataRequest
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

class RemoteDataSourceRepository @Inject constructor(
    val peopleApi: PeopleApi,
    val roomsApi: RoomsApi
) : IRemoteDataSourceRepository {
    override suspend fun getEmployeesFromApi(): DataRequest<PeopleResponse> {
        return try {
            val response = peopleApi.getPeople()
            if(response.isSuccessful){
                if(response.body()!!.isNotEmpty())
                    DataRequest.Success(response.body()!!)
                else DataRequest.Failed("Empty body")
            }else
                throw HttpException(response)


        }catch (e: HttpException) {
            val code = e.code()
                DataRequest.Failed("Http Request Failed with Error Code:$code")
            } catch (e: IOException) {
                DataRequest.Failed("Couldn't reach Server.. Check your Internet Connection")
    }}

    override suspend fun getRoomsFromApi(): DataRequest<RoomsResponse> {
        return try {
            val response = roomsApi.getRooms()
            if (response.isSuccessful) {
                if (response.body()!!.isNotEmpty())
                    DataRequest.Success(response.body()!!)
                else DataRequest.Failed("Empty body")
            } else
                throw HttpException(response)


        } catch (e: HttpException) {
            val code = e.code()
            DataRequest.Failed("Http Request Failed with Error Code:$code")
        } catch (e: IOException) {
            DataRequest.Failed("Couldn't reach Server.. Check your Internet Connection")
        }
    }
}






