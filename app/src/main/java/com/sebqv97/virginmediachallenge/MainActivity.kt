package com.sebqv97.virginmediachallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sebqv97.virginmediachallenge.data.apis.ApiConfig.retrofit
import com.sebqv97.virginmediachallenge.main.MainViewModel
import com.sebqv97.virginmediachallenge.util.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       val mainViewModel : MainViewModel by lazy { ViewModelProvider(this@MainActivity).get(MainViewModel::class.java) }

        mainViewModel.getData("rooms")

        mainViewModel.liveState.observe(this){ state->
            when(state){
                is UiState.Loading -> Log.d("Response","Loading")
                is UiState.Success<*> -> Log.d("Response", state.data.toString())
                is UiState.Failure<*> -> Log.d("Response",state.message.toString())
            }
        }
    }
}