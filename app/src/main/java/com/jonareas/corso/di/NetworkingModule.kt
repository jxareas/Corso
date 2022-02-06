package com.jonareas.corso.di

import com.jonareas.corso.data.networking.DogApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkingModule {

    @Provides
    fun provideBaseUrl() : String = "https://raw.githubusercontent.com"

    @Provides
    fun provideRetrofitClient(baseUrl : String) : Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    fun provideRestApiService(retrofit : Retrofit) : DogApi =
        retrofit.create(DogApi::class.java)


}