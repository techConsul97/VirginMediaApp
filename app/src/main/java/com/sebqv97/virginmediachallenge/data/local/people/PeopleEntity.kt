package com.sebqv97.virginmediachallenge.data.local.people

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sebqv97.virginmediachallenge.data.models.people.PeopleResponse
import com.sebqv97.virginmediachallenge.data.models.people.PersonItemModel

@Entity(tableName = "employees")
class PeopleEntity(val peopleModel :PeopleResponse) {
    @PrimaryKey(autoGenerate = true)
        var generated_id:Int = 0
}