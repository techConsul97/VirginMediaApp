package com.sebqv97.virginmediachallenge.repositories

import com.sebqv97.virginmediachallenge.data.models.people.PeopleResponse
import com.sebqv97.virginmediachallenge.data.models.room.RoomsResponse
import com.sebqv97.virginmediachallenge.data.remote.apis.people.PeopleApi
import com.sebqv97.virginmediachallenge.data.remote.apis.rooms.RoomsApi
import com.sebqv97.virginmediachallenge.util.DataRequest
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import retrofit2.Response

class RemoteDataSourceRepositoryTest {
lateinit var remoteDataSourceRepository: RemoteDataSourceRepository
    @Mock
    private lateinit var peopleApi:PeopleApi
    @Mock
    private lateinit var roomApi:RoomsApi


    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        remoteDataSourceRepository = RemoteDataSourceRepository(peopleApi,roomApi)
    }


    @Test
    fun `get empty response from people api`(){
        runBlocking {
            whenever(peopleApi.getPeople()).thenReturn(Response.success(PeopleResponse()))
            val result = remoteDataSourceRepository.getEmployeesFromApi()
            when(result){
                is DataRequest.Failed -> {
                    assertEquals("Empty body",result.message)
                }
                is DataRequest.Success -> {assert(false)}
            }

        }

    }
    @Test
    fun `get empty response from room api`(){
        runBlocking {
            whenever(roomApi.getRooms()).thenReturn(Response.success(RoomsResponse()))
            val result = remoteDataSourceRepository.getRoomsFromApi()
            when(result){
                is DataRequest.Failed -> {
                    assertEquals("Empty body",result.message)
                }
                is DataRequest.Success -> {assert(false)}
            }

        }

    }

@Test
fun `generate error response with code 404 for peopleApi`(){
    runBlocking {
        whenever(peopleApi.getPeople()).thenReturn(Response.error(404,"sone".toResponseBody()))
        val result = remoteDataSourceRepository.getEmployeesFromApi()
        when(result){
            is DataRequest.Failed -> {
                assertEquals("Http Request Failed with Error Code:404",result.message)
            }
            else -> {assert(false)}
        }

    }
}

    @Test
    fun `generate error response with code 600 for roomApi`(){
        runBlocking {
            whenever(roomApi.getRooms()).thenReturn(Response.error(600,"sone".toResponseBody()))
            val result = remoteDataSourceRepository.getRoomsFromApi()
            when(result){
                is DataRequest.Failed -> {
                    assertEquals("Http Request Failed with Error Code:600",result.message)
                }
                else -> {assert(false)}
            }

        }
    }
}