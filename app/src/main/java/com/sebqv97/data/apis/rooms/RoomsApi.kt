package com.sebqv97.data.apis.rooms

import com.sebqv97.data.models.room.RoomsResponse
import retrofit2.Response
import retrofit2.http.GET

interface RoomsApi {
    @GET("/api/v1/rooms")
    suspend fun getRooms():Response<RoomsResponse>
}