package com.jonareas.corso.viewmodel

import androidx.lifecycle.*
import com.jonareas.corso.data.dao.DogDao
import com.jonareas.corso.data.model.Dog
import com.jonareas.corso.data.networking.DogApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DogListViewModel @Inject constructor(private val dogApi: DogApi, private val dogDao: DogDao) :
    ViewModel() {

    private val _dogs = MutableLiveData<List<Dog>>()
    val dogs: LiveData<List<Dog>> = _dogs

    fun fetchFromRemote() {
        viewModelScope.launch(Dispatchers.Main) {
            _dogs.postValue(dogApi.getDogs())
            withContext(Dispatchers.IO) { _dogs.value?.let { storeDogsLocally(it) } }
        }
    }

    fun fetchFromDatabase() {
        viewModelScope.launch(Dispatchers.Default) {
            _dogs.postValue(dogDao.getAll())
        }
    }

    fun searchDatabase(query : String) : LiveData<List<Dog>> {
        return dogDao.getByName(query).asLiveData()
    }


    private fun storeDogsLocally(list: List<Dog>) {
        dogDao.deleteAll()
        val result = dogDao.insert(*list.toTypedArray())
        var i = 0
        while (i < list.size) {
            list[i].uuid = result[i].toInt()
            ++i
        }
    }


}