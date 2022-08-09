package com.sebqv97.virginmediachallenge.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.sebqv97.virginmediachallenge.R
import com.sebqv97.virginmediachallenge.adapters.PeopleAdapter
import com.sebqv97.virginmediachallenge.data.apis.ApiConfig
import com.sebqv97.virginmediachallenge.data.models.people.PeopleResponse
import com.sebqv97.virginmediachallenge.databinding.FragmentPeopleBinding
import com.sebqv97.virginmediachallenge.main.MainViewModel
import com.sebqv97.virginmediachallenge.ui.utils.AddOnClickListener
import com.sebqv97.virginmediachallenge.util.UiState
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.internal.notifyAll

@AndroidEntryPoint
class PeopleFragment : Fragment(R.layout.fragment_people) {
    private val mainViewModel: MainViewModel by lazy {
        ViewModelProvider(this@PeopleFragment)[MainViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentPeopleBinding.bind(view)


        //call the API
        mainViewModel.getData(ApiConfig.PEOPLE_ENDPOINT)

        mainViewModel.liveState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Loading -> Log.d("Response", "Loading")
                is UiState.Success<*> -> {
                    Log.d("Response", state.data.toString())


                    //set refresh layout Listener
                    binding.peopleSwipeRefreshLayout.setOnRefreshListener {
                        mainViewModel.getData(ApiConfig.PEOPLE_ENDPOINT)
                        binding.rvPeople.adapter.let { notifyAll() }
                        binding.peopleSwipeRefreshLayout.isRefreshing =false
                        Toast.makeText(context, "REFRESHED", Toast.LENGTH_SHORT).show()


                    }

                    //configure the recycler View
                    binding.rvPeople.run {

                        layoutManager = GridLayoutManager(requireContext(), 2)

                        //call the adapter with Context -> Resources and function that is used for mapping the onclickListener
                        adapter = PeopleAdapter(requireContext(),state.data as PeopleResponse,object : AddOnClickListener{
                            override fun onClick(position: Int) {

                                //extract the correct user

                                val user = state.data[position]
                                val detailsBundle = Bundle()
                                detailsBundle.putSerializable("user",user)

                                findNavController().navigate(R.id.action_mainFragment2_to_personDetailsFragment,detailsBundle)
                            }

                        })


                    }
                }
                is UiState.Failure<*> -> Log.d("Response", state.message.toString())
            }
        }


    }

}