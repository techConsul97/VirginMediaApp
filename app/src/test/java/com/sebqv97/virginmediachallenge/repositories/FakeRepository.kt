package com.sebqv97.virginmediachallenge.repositories

import android.provider.Contacts
import android.provider.ContactsContract
import androidx.lifecycle.LiveData
import com.sebqv97.virginmediachallenge.data.local.people.PeopleEntity
import com.sebqv97.virginmediachallenge.data.local.rooms.RoomsEntity
import com.sebqv97.virginmediachallenge.util.DataRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeRepository : IRepository {
    private val peopleEntityList = mutableListOf<PeopleEntity>()
    private val roomsEntityList = mutableListOf<RoomsEntity>()


  var readPeopleFromDb: LiveData<List<PeopleEntity>>? = null
  var readRoomsFromDb: LiveData<List<RoomsEntity>>? = null


    private val apiData = "API FAKING"
    override fun getAllEmployees(): Flow<List<PeopleEntity>> {
        return flow  { peopleEntityList }
    }


    override suspend fun insertEmployee(employeeEntity: PeopleEntity) {
       peopleEntityList.add(employeeEntity)
    }

    override suspend fun apiMappper(requestedData: String): DataRequest<*> {
      return  when(requestedData){
            "Error" -> DataRequest.TestFailed<String>("Failed")
             "Success"-> DataRequest.TestSuccess<String>(apiData)
          else -> DataRequest.Loading(apiData)
        }
    }

    override fun getAllRooms(): Flow<List<RoomsEntity>> {
       return flow {  roomsEntityList }
    }


    override suspend fun insertRoom(roomEntity: RoomsEntity) {
       roomsEntityList.add(roomEntity)
    }
}