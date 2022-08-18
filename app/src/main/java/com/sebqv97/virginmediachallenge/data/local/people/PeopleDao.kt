package com.sebqv97.virginmediachallenge.data.local.people

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.sebqv97.virginmediachallenge.data.models.people.PeopleResponse
import com.sebqv97.virginmediachallenge.data.models.people.PersonItemModel
import kotlinx.coroutines.flow.Flow


@Dao
interface PeopleDao {

    @Query("SELECT * FROM employees order by generated_id ASC")
     fun readAllPeople() : Flow<List<PeopleEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEmployee(employee : PeopleEntity)

}