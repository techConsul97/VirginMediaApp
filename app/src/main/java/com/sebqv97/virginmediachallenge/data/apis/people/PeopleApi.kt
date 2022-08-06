package com.sebqv97.virginmediachallenge.data.apis.people

import com.sebqv97.virginmediachallenge.data.models.people.PeopleResponse
import retrofit2.Response
import retrofit2.http.GET

interface PeopleApi {
    @GET("/api/v1/people")
   suspend fun getPeople(): Response<PeopleResponse>

}