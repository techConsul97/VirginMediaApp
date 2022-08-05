package com.sebqv97.virginmediachallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sebqv97.virginmediachallenge.data.apis.ApiConfig.retrofit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}