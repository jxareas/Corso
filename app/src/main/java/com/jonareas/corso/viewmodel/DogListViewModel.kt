package com.jonareas.corso.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jonareas.corso.networking.DogApiService
import kotlinx.coroutines.async

class DogListViewModel : ViewModel() {

    private val dogsService = DogApiService()

    suspend fun fetchFromRemote() = viewModelScope.async {
        dogsService.getDogs()
    }.await()


}