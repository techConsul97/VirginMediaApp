package com.sebqv97.virginmediachallenge.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sebqv97.virginmediachallenge.R
import com.sebqv97.virginmediachallenge.data.models.room.RoomsResponse
import com.sebqv97.virginmediachallenge.databinding.RoomLayoutBinding

class RoomsRvAdapter(private val mList: RoomsResponse) : RecyclerView.Adapter<RoomsRvAdapter.RoomsViewHolder>() {
    inner class RoomsViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val binding  = RoomLayoutBinding.bind(itemView)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomsViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.room_layout,parent,false)
        return RoomsViewHolder(view)
    }


    override fun onBindViewHolder(holder: RoomsViewHolder, position: Int) {
       holder.binding.apply {
           var element = mList[position]
            textViewAvailability.text = if(element.isOccupied) "OCCUPIED" else "AVAILABLE"
           textViewOccupancy.text = "#${element.id}"



       }
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    override fun getItemCount(): Int {
        return mList.size
    }
}