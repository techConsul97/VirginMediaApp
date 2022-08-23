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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    var mainRepository: IRepository
) : ViewModel() {

    private  var _liveState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)
    val liveState: StateFlow<UiState> get() = _liveState

    var readPeopleFromDb: LiveData<List<PeopleEntity>>? = null
    var readRoomsFromDb: LiveData<List<RoomsEntity>>? = null

    fun getDataFromAPi(requestedData: String) = CoroutineScope(Dispatchers.IO).launch {

        val response = mainRepository.apiMappper(requestedData)

        when (response) {
            is DataRequest.Success -> {


                //Insert data to db
                when (requestedData) {
                    ApiConfig.PEOPLE_ENDPOINT -> mainRepository.insertEmployee(
                        PeopleEntity(response.data as PeopleResponse)
                    )
                    ApiConfig.ROOMS_ENDPOINT -> mainRepository.insertRoom(
                        RoomsEntity(
                            response.data as RoomsResponse
                        )
                    )
                }

                _liveState.value = response.data.let { bodyData -> UiState.Success(bodyData) }

            }
            is DataRequest.Failed -> {
                _liveState.value = response.message.let { error -> UiState.Failure(error) }
            }


        }

    }

    fun getDataFromDB(requestedData: String) = when (requestedData) {
        ApiConfig.PEOPLE_ENDPOINT -> readPeopleFromDb =
            mainRepository.getAllEmployees().asLiveData()
        ApiConfig.ROOMS_ENDPOINT -> readRoomsFromDb =
            mainRepository.getAllRooms().asLiveData()
        else -> throw Throwable("Bad DB Request")
    }


}