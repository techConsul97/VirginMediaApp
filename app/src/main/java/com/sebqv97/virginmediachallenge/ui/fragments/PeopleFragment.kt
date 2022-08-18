package com.sebqv97.virginmediachallenge.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.sebqv97.virginmediachallenge.R
import com.sebqv97.virginmediachallenge.adapters.PeopleAdapter
import com.sebqv97.virginmediachallenge.data.models.people.PeopleResponse
import com.sebqv97.virginmediachallenge.data.remote.apis.ApiConfig
import com.sebqv97.virginmediachallenge.databinding.FragmentPeopleBinding
import com.sebqv97.virginmediachallenge.repositories.MainViewModel
import com.sebqv97.virginmediachallenge.ui.utils.AddOnClickListener
import com.sebqv97.virginmediachallenge.util.UiState
import com.sebqv97.virginmediachallenge.util.checkForInternet
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PeopleFragment : Fragment(R.layout.fragment_people) {
    private val mainViewModel: MainViewModel by lazy {
        ViewModelProvider(this@PeopleFragment)[MainViewModel::class.java]
    }

    private lateinit var binding: FragmentPeopleBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPeopleBinding.bind(view)

        if (checkForInternet(requireContext())) {
            // IF Connected
            //Get data from api and save it locally(done in viewModel)
            //call the API
            getPeopleFromApi()

            Toast.makeText(requireContext(), "Data from API", Toast.LENGTH_SHORT).show()
        } else {
            //No Internet Connection
            // Get data from DB
            getPeopleFromDB()

            Toast.makeText(requireContext(), "Data from DB", Toast.LENGTH_SHORT).show()
        }

        //set refresh layout Listener
        binding.peopleSwipeRefreshLayout.setOnRefreshListener {
            mainViewModel.getDataFromAPi(ApiConfig.PEOPLE_ENDPOINT)
            binding.peopleSwipeRefreshLayout.isRefreshing = false
            Toast.makeText(context, "REFRESHED", Toast.LENGTH_SHORT).show()


        }


    }

    private fun getPeopleFromApi() {

        mainViewModel.getDataFromAPi(ApiConfig.PEOPLE_ENDPOINT)

        mainViewModel.liveState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Loading -> Log.d("Response", "Loading")
                is UiState.Success<*> -> {
                    Log.d("Response", state.data.toString())
                    updateUi(state.data as PeopleResponse)
                }
                is UiState.Failure<*> -> Log.d("Response", state.message.toString())
            }
        }
    }

    private fun getPeopleFromDB() {

        mainViewModel.getDataFromDB(ApiConfig.PEOPLE_ENDPOINT)
        mainViewModel.readPeopleFromDb!!.observe(viewLifecycleOwner) { database ->
            if (database.isNotEmpty()) {
                Log.d("Beers", "Data from database")
                updateUi(database[0].peopleModel)
                mainViewModel.readPeopleFromDb = null

            } else {
                throw Throwable("No data in the DB")
            }
        }
    }

    private fun updateUi(people: PeopleResponse) {
        //configure the recycler View
        binding.rvPeople.run {

            layoutManager = GridLayoutManager(requireContext(), 2)

            //call the adapter with Context -> Resources and function that is used for mapping the onclickListener
            adapter = PeopleAdapter(requireContext(), people, object : AddOnClickListener {
                override fun onClick(position: Int) {

                    //extract the correct user

                    val user = people[position]
                    val detailsBundle = Bundle()
                    detailsBundle.putSerializable("user", user)

                    findNavController().navigate(
                        R.id.action_mainFragment2_to_personDetailsFragment,
                        detailsBundle
                    )
                }

            })


        }
    }


}