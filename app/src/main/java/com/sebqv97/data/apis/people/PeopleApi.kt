package com.sebqv97.data.apis.people

import com.sebqv97.data.models.people.PeopleResponse
import retrofit2.Response
import retrofit2.http.GET

interface PeopleApi {
    @GET("/api/v1/people")
    fun getPeople(): Response<PeopleResponse>

}