package com.sebqv97.repositories




import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import com.sebqv97.virginmediachallenge.data.models.people.PeopleResponse
import com.sebqv97.virginmediachallenge.data.remote.apis.ApiConfig
import com.sebqv97.virginmediachallenge.repositories.IRepository
import com.sebqv97.virginmediachallenge.repositories.MainViewModel
import com.sebqv97.virginmediachallenge.repositories.Repository
import com.sebqv97.virginmediachallenge.util.DataRequest
import com.sebqv97.virginmediachallenge.util.UiState
import kotlinx.coroutines.Dispatchers
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
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

class MainViewModelApiTest {

    private lateinit var mainViewModel: MainViewModel

    val dispatchers = TestCoroutineDispatcher()

    @get:Rule
    //tell the application to run the tests INSTANTLY, HIGH PRIORITY
    val rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: IRepository

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatchers)
        MockitoAnnotations.openMocks(this)
        mainViewModel = MainViewModel(repository)

    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun getEmptyPeopleListResponse(): Unit = runBlocking {
        whenever(repository.apiMappper(ApiConfig.PEOPLE_ENDPOINT)).thenReturn(
            DataRequest.Failed<PeopleResponse>("empty list")
        )

        mainViewModel.getDataFromAPi(ApiConfig.PEOPLE_ENDPOINT)

        mainViewModel.liveState.asLiveData().observeForever {
            assertEquals("empty list",(it as UiState.Failure<*>).message)
        }


    }

}