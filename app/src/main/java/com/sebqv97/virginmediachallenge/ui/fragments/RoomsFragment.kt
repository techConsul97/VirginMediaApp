package com.sebqv97.virginmediachallenge.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.sebqv97.virginmediachallenge.R
import com.sebqv97.virginmediachallenge.adapters.RoomsRvAdapter
import com.sebqv97.virginmediachallenge.data.models.room.RoomsResponse
import com.sebqv97.virginmediachallenge.data.remote.apis.ApiConfig
import com.sebqv97.virginmediachallenge.databinding.FragmentRoomsBinding
import com.sebqv97.virginmediachallenge.repositories.MainViewModel
import com.sebqv97.virginmediachallenge.util.UiState
import com.sebqv97.virginmediachallenge.util.checkForInternet
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RoomsFragment : Fragment(R.layout.fragment_rooms) {
    private val mainViewModel: MainViewModel by lazy {
        ViewModelProvider(this@RoomsFragment).get(
            MainViewModel::class.java
        )
    }
    lateinit var binding: FragmentRoomsBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRoomsBinding.bind(view)

        //Check internet connection
        if (checkForInternet(requireContext())) {
            //CONNECTED -> Request data from API
            requestRoomsFromApi()
            Toast.makeText(requireContext(), "Data from API", Toast.LENGTH_SHORT).show()

        } else {
            requestRoomsFromDB()
            Toast.makeText(requireContext(), "Data from DB", Toast.LENGTH_SHORT).show()
        }


        binding.roomsSwipeRefreshLayout.setOnRefreshListener {
            mainViewModel.getDataFromAPi(ApiConfig.ROOMS_ENDPOINT)
            binding.roomsSwipeRefreshLayout.isRefreshing = false
            Toast.makeText(context, "REFRESHED", Toast.LENGTH_SHORT).show()

        }

    }

    private fun requestRoomsFromDB() {
        mainViewModel.getDataFromDB(ApiConfig.ROOMS_ENDPOINT)
        mainViewModel.readRoomsFromDb!!.observe(viewLifecycleOwner) { database ->
            if (database.isNotEmpty()) {
                Log.d("Beers", "Data from database")
                updateUi(database[0].room)
                mainViewModel.readRoomsFromDb = null

            } else {
                throw Throwable("No data in the DB")
            }
        }
    }

    private fun requestRoomsFromApi() {
        mainViewModel.getDataFromAPi(ApiConfig.ROOMS_ENDPOINT)

        CoroutineScope(Dispatchers.IO).launch {  mainViewModel.liveState.collect{ state ->
            when (state) {
                is UiState.Loading -> Log.d("Response", "Loading")
                is UiState.Success<*> -> {
                    Log.d("Response", state.data.toString())
                    updateUi(state.data as RoomsResponse)
                }
                is UiState.Failure<*> -> Log.d("Response", state.message.toString())
            }
        } }

    }

    private fun updateUi(rooms: RoomsResponse) {
        binding.rvRooms.run {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = RoomsRvAdapter(context = requireContext(), mList = rooms)
        }
    }


}