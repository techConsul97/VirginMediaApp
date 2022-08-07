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
import com.sebqv97.virginmediachallenge.data.apis.ApiConfig
import com.sebqv97.virginmediachallenge.data.models.room.RoomsResponse
import com.sebqv97.virginmediachallenge.databinding.FragmentRoomsBinding
import com.sebqv97.virginmediachallenge.main.MainViewModel
import com.sebqv97.virginmediachallenge.util.UiState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RoomsFragment : Fragment(R.layout.fragment_rooms) {
           private val mainViewModel : MainViewModel by lazy { ViewModelProvider(this@RoomsFragment).get(MainViewModel::class.java) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentRoomsBinding.bind(view)

        mainViewModel.getData(ApiConfig.ROOMS_ENDPOINT)

        mainViewModel.liveState.observe(viewLifecycleOwner){ state->
            when(state){
                is UiState.Loading -> Log.d("Response","Loading")
                is UiState.Success<*> -> {
                    Log.d("Response", state.data.toString())
                    binding.rvRooms.run {
                        layoutManager = GridLayoutManager(requireContext(),2)
                        adapter = RoomsRvAdapter(state.data as RoomsResponse)
                    }
                }
                is UiState.Failure<*> -> Log.d("Response",state.message.toString())
            }
        }

        binding.roomsSwipeRefreshLayout.setOnRefreshListener {
            mainViewModel.getData(ApiConfig.ROOMS_ENDPOINT)
            binding.rvRooms.adapter!!.notifyDataSetChanged()
            binding.roomsSwipeRefreshLayout.isRefreshing = false
            Toast.makeText(context,"REFRESHED",Toast.LENGTH_SHORT).show()

        }

    }


}