package com.sebqv97.virginmediachallenge.di.modules

import com.sebqv97.virginmediachallenge.data.local.people.PeopleDao
import com.sebqv97.virginmediachallenge.data.local.rooms.RoomsDao
import com.sebqv97.virginmediachallenge.data.remote.apis.people.PeopleApi
import com.sebqv97.virginmediachallenge.data.remote.apis.rooms.RoomsApi
import com.sebqv97.virginmediachallenge.repositories.IRepository
import com.sebqv97.virginmediachallenge.repositories.LocalDataSourceRepository
import com.sebqv97.virginmediachallenge.repositories.RemoteDataSourceRepository
import com.sebqv97.virginmediachallenge.repositories.Repository
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


    @Provides
    fun provideRemoteDataSourceReference(peopleApi: PeopleApi,roomsApi: RoomsApi) = RemoteDataSourceRepository(peopleApi,roomsApi)

    @Provides
    fun provideRepositoryReference(roomsDao: RoomsDao,peopleDao: PeopleDao,remoteDataSourceRepository: RemoteDataSourceRepository):IRepository = Repository(roomsDao,peopleDao,remoteDataSourceRepository)

}