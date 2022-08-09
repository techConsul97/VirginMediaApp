package com.sebqv97.virginmediachallenge.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sebqv97.virginmediachallenge.R
import com.sebqv97.virginmediachallenge.data.models.room.RoomsResponse
import com.sebqv97.virginmediachallenge.databinding.RoomLayoutBinding
import com.squareup.picasso.Picasso

class RoomsRvAdapter(
    private val mList: RoomsResponse,
    private val context: Context) :
    RecyclerView.Adapter<RoomsRvAdapter.RoomsViewHolder>() {
    class RoomsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = RoomLayoutBinding.bind(itemView)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomsViewHolder =
        RoomsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.room_layout, parent, false)
        )


    override fun onBindViewHolder(holder: RoomsViewHolder, position: Int) {
        holder.binding.apply {
            val element = mList[position]
            textViewAvailability.text = if (element.isOccupied) "OCCUPIED" else "AVAILABLE"
            textViewId.text = context.resources.getString(R.string.room_id,element.id)
            Picasso.get()
                .load(context.resources.getString(R.string.rooms_image))
                .error(R.drawable.no_picture_available_icon_1).into(image)


        }
    }


    override fun getItemCount(): Int = mList.size

}