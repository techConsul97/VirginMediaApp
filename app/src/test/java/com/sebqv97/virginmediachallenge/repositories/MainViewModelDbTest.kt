@file:OptIn(ExperimentalCoroutinesApi::class)

package com.sebqv97.virginmediachallenge.repositories

import android.os.Build.VERSION_CODES.P
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.sebqv97.virginmediachallenge.data.local.people.PeopleEntity
import com.sebqv97.virginmediachallenge.data.local.rooms.RoomsEntity
import com.sebqv97.virginmediachallenge.data.models.people.PeopleResponse
import com.sebqv97.virginmediachallenge.data.models.room.RoomsResponse
import com.sebqv97.virginmediachallenge.data.remote.apis.ApiConfig
import com.sebqv97.virginmediachallenge.util.getOrAwaitValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.security.Policy

@RunWith(JUnit4::class)
class MainViewModelDbTest {
    private lateinit var repository: FakeRepository
    private lateinit var mainViewModel: MainViewModel

    private lateinit var peopleList: MutableList<PeopleEntity>
    private lateinit var roomsList: MutableList<RoomsEntity>
    private val typesOfDb = listOf(ApiConfig.PEOPLE_ENDPOINT, ApiConfig.ROOMS_ENDPOINT)
    val dispatchers = TestCoroutineDispatcher()

    @get:Rule
    //tell the application to run the tests INSTANTLY, HIGH PRIORITY
    val rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatchers)
        repository = FakeRepository()
        mainViewModel = MainViewModel((repository))
        peopleList = mutableListOf<PeopleEntity>()
        roomsList = mutableListOf<RoomsEntity>()

    }

    @Test
    fun `check if initial state of dbs are empty`() {

        //call for each db the getter
        for (index in typesOfDb.indices) {
            //0 -> People, 1-> Rooms
            mainViewModel.getDataFromDB(typesOfDb[index])
            if (index == 0) {
                val peopleData = mainViewModel.readPeopleFromDb!!.observeForever {
                    assertEquals(listOf<PeopleEntity>(), it)
                }

            } else {
                val roomsData = mainViewModel.readRoomsFromDb!!.observeForever{
                    assertEquals(listOf<RoomsEntity>(), it)
                }

            }


        }
    }


    @Test
    fun `insert 4 elements into each table, check for size 4`() {
        //load data into lists
        loadDummyDataIntoLists(
            PeopleEntity(PeopleResponse()),
            RoomsEntity(RoomsResponse()),
            4
        )
        runBlocking {
            for (index in typesOfDb.indices) {
                //0 -> People, 1-> Rooms
                val currentList = if (index == 0) peopleList else roomsList
                for (entity in currentList) {
                    if (index == 0)
                        mainViewModel.mainRepository.insertEmployee(entity as PeopleEntity)
                    else
                        mainViewModel.mainRepository.insertRoom(entity as RoomsEntity)
                }

            }

            //get the values
            mainViewModel.getDataFromDB(ApiConfig.PEOPLE_ENDPOINT)
            val peopleData = mainViewModel.readPeopleFromDb!!.observeForever {
                assertEquals(4, it.size)
            }

            mainViewModel.getDataFromDB(ApiConfig.ROOMS_ENDPOINT)
            val roomData = mainViewModel.readRoomsFromDb!!.observeForever {
                assertEquals(4, it.size)

            }

        }

    }

    fun loadDummyDataIntoLists(peopleEntity: PeopleEntity, roomsEntity: RoomsEntity, xtimes: Int) {
        for (index in 0 until xtimes) {
            peopleList.add(peopleEntity)
            roomsList.add(roomsEntity)
        }

    }

    @After
    fun tearDown() {
        peopleList = mutableListOf()
        roomsList = mutableListOf()
        Dispatchers.resetMain()

    }
}
