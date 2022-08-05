package com.sebqv97.virginmediachallenge.main

import com.sebqv97.virginmediachallenge.data.models.people.PeopleResponse
import com.sebqv97.virginmediachallenge.data.models.room.RoomsResponse
import com.sebqv97.virginmediachallenge.util.DataRequest

interface MainRepository {

    suspend fun getPeople():DataRequest<PeopleResponse>
    suspend fun getRooms():DataRequest<RoomsResponse>
}