package com.sebqv97.virginmediachallenge.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

import androidx.room.TypeConverters
import com.sebqv97.virginmediachallenge.data.local.people.PeopleDao
import com.sebqv97.virginmediachallenge.data.local.people.PeopleEntity
import com.sebqv97.virginmediachallenge.data.local.people.PeopleTypeConverter
import com.sebqv97.virginmediachallenge.data.local.rooms.RoomsDao
import com.sebqv97.virginmediachallenge.data.local.rooms.RoomsEntity


@Database(
    entities = [PeopleEntity::class,RoomsEntity::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(PeopleTypeConverter::class,RoomsTypeConverter::class)
abstract class RoomLocalDatabase : RoomDatabase(){
    abstract fun peopleDao() : PeopleDao
    abstract fun roomsDao() : RoomsDao
}