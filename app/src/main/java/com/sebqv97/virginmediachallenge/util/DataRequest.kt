package com.sebqv97.virginmediachallenge.util

sealed class DataRequest<T>(val data:T?, val message:String?){
    class Success<T>(data: T) : DataRequest<T>(data,null)
    class Failed<T>(message: String) : DataRequest<T>(null,message)
}
