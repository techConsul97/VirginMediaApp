package com.sebqv97.virginmediachallenge.di.modules

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    const val BASE_URL = "https://61e947967bc0550017bc61bf.mockapi.io"

    @Provides
    fun getGsonReference() = Gson()


    @Provides
    fun getOkHttpInterceptorReference(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    fun getOkHttpReference(httpLoggingInterceptor: HttpLoggingInterceptor) = OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)

    @Provides
    fun getRetrofitReference(okHttpClient: OkHttpClient, gson: Gson) =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL).
            addConverterFactory(GsonConverterFactory.create(gson))
            .build()
}