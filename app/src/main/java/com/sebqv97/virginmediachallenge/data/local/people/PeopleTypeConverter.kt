package com.sebqv97.virginmediachallenge.data.local.people

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sebqv97.virginmediachallenge.data.models.people.PeopleResponse

class PeopleTypeConverter {

    val gson = Gson()

    @TypeConverter
    fun entitiesToString(entities : PeopleResponse):String = gson.toJson(entities)


    @TypeConverter
    fun stringToEntities(data:String): PeopleResponse {
        val listType = object : TypeToken<PeopleResponse>() {}.type
        return gson.fromJson(data,listType)
    }


}