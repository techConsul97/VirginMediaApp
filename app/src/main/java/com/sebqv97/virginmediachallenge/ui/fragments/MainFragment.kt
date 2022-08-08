package com.sebqv97.virginmediachallenge.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.sebqv97.virginmediachallenge.R
import com.sebqv97.virginmediachallenge.adapters.ViewPagerAdapter
import com.sebqv97.virginmediachallenge.databinding.FragmentMainBinding

class MainFragment : Fragment(R.layout.fragment_main) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentMainBinding.bind(view)
        val viewPagerAdapter = ViewPagerAdapter(parentFragmentManager,lifecycle)
        binding.viewPager2.adapter = viewPagerAdapter

        //tab Layout
        TabLayoutMediator(binding.tabLayout,binding.viewPager2){
            tab,position -> when(position){
                0-> tab.setText("People")
                1-> tab.setText("Rooms")
            }
        }.attach()


    }
}