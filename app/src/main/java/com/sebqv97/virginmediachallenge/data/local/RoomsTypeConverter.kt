package com.sebqv97.virginmediachallenge.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sebqv97.virginmediachallenge.data.models.room.RoomsResponse

class RoomsTypeConverter {

    val gson = Gson()

    @TypeConverter
    fun entitiesToString(entities : RoomsResponse):String = gson.toJson(entities)


    @TypeConverter
    fun stringToEntities(data:String): RoomsResponse {
        val listType = object : TypeToken<RoomsResponse>() {}.type
        return gson.fromJson(data,listType)
    }


}