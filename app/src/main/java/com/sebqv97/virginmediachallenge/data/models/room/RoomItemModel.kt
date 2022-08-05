package com.sebqv97.virginmediachallenge.data.models.room


import com.google.gson.annotations.SerializedName

data class RoomItemModel(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("isOccupied")
    val isOccupied: Boolean,
    @SerializedName("maxOccupancy")
    val maxOccupancy: Int
)