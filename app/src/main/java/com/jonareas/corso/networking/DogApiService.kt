package com.jonareas.corso.networking

import com.jonareas.corso.data.model.Dog
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DogApiService {

    private val BASE_URL = "https://raw.githubusercontent.com"

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(DogApi::class.java)

    suspend fun getDogs() : List<Dog> = api.getDogs()

}