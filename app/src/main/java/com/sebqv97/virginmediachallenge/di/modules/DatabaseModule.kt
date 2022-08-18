package com.sebqv97.virginmediachallenge.di.modules

import android.content.Context
import androidx.room.Room
import com.sebqv97.virginmediachallenge.data.local.RoomLocalDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {


    @Provides
    fun provideRoomInstance(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        RoomLocalDatabase::class.java,
        "BeersDatabase"
    ).build()

    @Provides
    fun providePeopleDao(database: RoomLocalDatabase) = database.peopleDao()

    @Provides
    fun provideRoomsDao(database: RoomLocalDatabase) = database.roomsDao()
}
