package com.sebqv97.virginmediachallenge.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sebqv97.virginmediachallenge.util.DataRequest
import com.sebqv97.virginmediachallenge.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    var mainRepository: MainRepositoryImpl
) : ViewModel() {

    private var _liveState: MutableLiveData<UiState> = MutableLiveData(UiState.Loading)
    val liveState: LiveData<UiState> get() = _liveState

    fun getData(requestedData: String) = CoroutineScope(Dispatchers.IO).launch {

        val response = mainRepository.apiMapper(requestedData)

        when (response) {
            is DataRequest.Success -> {
                _liveState.postValue(
                    response.data.let { bodyData -> UiState.Success(bodyData) }
                )
            }
            is DataRequest.Failed -> {
                _liveState.postValue(response.message.let { error -> UiState.Failure(error) })
            }


        }

    }


}