package com.sebqv97.virginmediachallenge.di.modules

import com.sebqv97.virginmediachallenge.data.apis.people.PeopleApi
import com.sebqv97.virginmediachallenge.data.apis.rooms.RoomsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun providePeopleApi(retrofit: Retrofit):PeopleApi = retrofit.create(PeopleApi::class.java)


    @Provides
    fun provideRoomsApi(retrofit: Retrofit):RoomsApi = retrofit.create(RoomsApi::class.java)
}