package com.sebqv97.virginmediachallenge.data.local.rooms

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sebqv97.virginmediachallenge.data.models.room.RoomsResponse


@Entity(tableName = "rooms")
class RoomsEntity(val room:RoomsResponse) {
    @PrimaryKey(autoGenerate = true)
    var id :Int = 0

}