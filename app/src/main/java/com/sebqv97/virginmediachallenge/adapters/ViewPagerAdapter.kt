package com.sebqv97.virginmediachallenge.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sebqv97.virginmediachallenge.ui.fragments.PeopleFragment
import com.sebqv97.virginmediachallenge.ui.fragments.RoomsFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {
    private val tabCount = 2
    override fun getItemCount() = tabCount



    override fun createFragment(position: Int): Fragment  =
        when(position){
            0 -> PeopleFragment()
            else -> RoomsFragment()
        }




}