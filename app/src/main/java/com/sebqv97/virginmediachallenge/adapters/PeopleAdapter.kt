package com.sebqv97.virginmediachallenge.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.sebqv97.virginmediachallenge.R
import com.sebqv97.virginmediachallenge.data.models.people.PeopleResponse
import com.sebqv97.virginmediachallenge.data.models.people.PersonItemModel
import com.sebqv97.virginmediachallenge.databinding.PeopleLayoutBinding
import com.sebqv97.virginmediachallenge.ui.utils.AddOnClickListener
import com.squareup.picasso.Picasso

class PeopleAdapter(
    private val mList: PeopleResponse,
    private val clickListener: AddOnClickListener) : RecyclerView.Adapter<PeopleAdapter.PeopleViewHolder>() {
     class PeopleViewHolder(itemView: View,clickAtPosition: (Int) -> Unit) : RecyclerView.ViewHolder(itemView) {
        val binding = PeopleLayoutBinding.bind(itemView)
         init {
             itemView.setOnClickListener{clickAtPosition(absoluteAdapterPosition)}
         }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.people_layout, parent, false)
        return PeopleViewHolder(view){
          //  clickListener(mList[it])
        }
    }


    override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) {
        holder.binding.apply {
            val element = mList[position]
            textViewFullName.text = "${element.firstName} ${element.lastName}"
            textViewRole.text = element.jobtitle
            Picasso.get()
                .load(element.avatar)
                .error(R.drawable.no_picture_available_icon_1)
                .into(imageViewUseImage)
        }
        holder.itemView.setOnClickListener{clickListener.onClick(position)}
    }


    override fun getItemCount(): Int {
        return mList.size
    }
}