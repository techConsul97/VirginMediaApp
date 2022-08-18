package com.sebqv97.virginmediachallenge.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.sebqv97.virginmediachallenge.data.local.people.PeopleEntity
import com.sebqv97.virginmediachallenge.data.local.rooms.RoomsEntity
import com.sebqv97.virginmediachallenge.data.models.people.PeopleResponse
import com.sebqv97.virginmediachallenge.data.models.room.RoomsResponse
import com.sebqv97.virginmediachallenge.data.remote.apis.ApiConfig
import com.sebqv97.virginmediachallenge.util.DataRequest
import com.sebqv97.virginmediachallenge.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    var mainRepository: Repository
) : ViewModel() {

    private var _liveState: MutableLiveData<UiState> = MutableLiveData(UiState.Loading)
    val liveState: LiveData<UiState> get() = _liveState

    var readPeopleFromDb: LiveData<List<PeopleEntity>>? = null
    var readRoomsFromDb: LiveData<List<RoomsEntity>>? = null

    fun getDataFromAPi(requestedData: String) = CoroutineScope(Dispatchers.IO).launch {

        val response = mainRepository.apiMapper(requestedData)

        when (response) {
            is DataRequest.Success -> {


                //Insert data to db
                when (requestedData) {
                    ApiConfig.PEOPLE_ENDPOINT -> mainRepository.localAccess.insertEmployee(
                        PeopleEntity(response.data as PeopleResponse)
                    )
                    ApiConfig.ROOMS_ENDPOINT -> mainRepository.localAccess.insertRoom(
                        RoomsEntity(
                            response.data as RoomsResponse
                        )
                    )
                }

                _liveState.postValue(
                    response.data.let { bodyData -> UiState.Success(bodyData) }
                )
            }
            is DataRequest.Failed -> {
                _liveState.postValue(response.message.let { error -> UiState.Failure(error) })
            }


        }

    }

    fun getDataFromDB(requestedData: String) = when (requestedData) {
        ApiConfig.PEOPLE_ENDPOINT -> readPeopleFromDb =
            mainRepository.localAccess.getAllEmployees().asLiveData()
        ApiConfig.ROOMS_ENDPOINT -> readRoomsFromDb =
            mainRepository.localAccess.getAllRooms().asLiveData()
        else -> throw Throwable("Bad DB Request")
    }


}