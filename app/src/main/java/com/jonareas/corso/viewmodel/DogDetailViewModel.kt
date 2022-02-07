package com.jonareas.corso.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jonareas.corso.data.dao.DogDao
import com.jonareas.corso.data.model.Dog
import kotlinx.coroutines.launch
import javax.inject.Inject

class DogDetailViewModel @Inject constructor(private val dogDao : DogDao) : ViewModel()  {
    var _dog = MutableLiveData<Dog>()
    val dog : LiveData<Dog> = _dog

    fun fetchFromLocal(uuid : Int) {
        viewModelScope.launch { _dog.postValue(dogDao.getById(uuid)) }
    }

}