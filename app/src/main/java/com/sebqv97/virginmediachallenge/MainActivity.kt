package com.sebqv97.virginmediachallenge

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.sebqv97.virginmediachallenge.adapters.ViewPagerAdapter
import com.sebqv97.virginmediachallenge.databinding.ActivityMainBinding
import com.sebqv97.virginmediachallenge.ui.fragments.PeopleFragment
import com.sebqv97.virginmediachallenge.ui.fragments.RoomsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var  binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

binding = ActivityMainBinding.inflate(layoutInflater)
        //create adapter for viewPager2

val viewPagerAdapter  = ViewPagerAdapter(fragmentManager = supportFragmentManager, lifecycle = lifecycle)

        binding.viewPager2.adapter = viewPagerAdapter

        TabLayoutMediator(binding.tabLayout,binding.viewPager2){
            tab,position -> when(position){
                0 -> tab.setText("People")
                1 ->tab.setText("Rooms")
            }
        }.attach()

        setContentView(binding.root)


    }
}























//       val mainViewModel : MainViewModel by lazy { ViewModelProvider(this@MainActivity).get(MainViewModel::class.java) }
//
//        mainViewModel.getData("people")
//
//        mainViewModel.liveState.observe(this){ state->
//            when(state){
//                is UiState.Loading -> Log.d("Response","Loading")
//                is UiState.Success<*> -> Log.d("Response", state.data.toString())
//                is UiState.Failure<*> -> Log.d("Response",state.message.toString())
//            }
//        }