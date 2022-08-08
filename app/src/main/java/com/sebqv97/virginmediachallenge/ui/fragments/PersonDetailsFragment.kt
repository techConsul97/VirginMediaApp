package com.sebqv97.virginmediachallenge.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.sebqv97.virginmediachallenge.R
import com.sebqv97.virginmediachallenge.data.models.people.PersonItemModel
import com.sebqv97.virginmediachallenge.databinding.FragmentUserDetailsBinding
import com.squareup.picasso.Picasso

import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PersonDetailsFragment : Fragment(R.layout.fragment_user_details) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentUserDetailsBinding.bind(view)
        val user = requireArguments().get("user") as PersonItemModel

        binding.apply {
                textViewName.text = "${user.firstName} ${user.lastName}"
                textViewCreated.text = "Created at: ${user.createdAt?.substring(0,10)}"
                Picasso.get().load(user.avatar).error(R.drawable.no_picture_available_icon_1).into(imageViewUserAvatar)
                textViewEmail.text = user.email
                textViewJob.text = user.jobtitle
                textViewColor.text = user.favouriteColor




        }


    }
}