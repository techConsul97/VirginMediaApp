package com.sebqv97.virginmediachallenge.data.local.rooms

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sebqv97.virginmediachallenge.data.models.room.RoomItemModel
import com.sebqv97.virginmediachallenge.data.models.room.RoomsResponse
import kotlinx.coroutines.flow.Flow


@Dao
interface RoomsDao {

    @Query("SELECT * FROM rooms")
     fun readAllRooms() : Flow<List<RoomsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  insertRoom(room:RoomsEntity)
}