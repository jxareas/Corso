package com.jonareas.corso.data.networking

import com.jonareas.corso.data.model.Dog
import retrofit2.http.GET

interface DogApi {
    @GET("DevTides/DogsApi/master/dogs.json")
    suspend fun getDogs() : List<Dog>
}