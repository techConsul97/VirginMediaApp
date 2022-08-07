package com.sebqv97.virginmediachallenge.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.sebqv97.virginmediachallenge.R
import com.sebqv97.virginmediachallenge.databinding.UserDetailsBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PersonDetailsFragment : Fragment(R.layout.user_details) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = UserDetailsBinding.bind(view)
        val user = requireArguments().get("user")
        Toast.makeText(context,user.toString(),Toast.LENGTH_SHORT).show()


    }
}