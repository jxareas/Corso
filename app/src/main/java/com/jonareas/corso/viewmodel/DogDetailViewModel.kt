package com.jonareas.corso.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jonareas.corso.data.dao.DogDao
import com.jonareas.corso.data.model.Dog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogDetailViewModel @Inject constructor(private val dogDao : DogDao) : ViewModel()  {
    private var _selectedDog = MutableLiveData<Dog>()
    val selectedDog : LiveData<Dog> = _selectedDog


    fun fetchDogFromLocalDatabase(uuid : Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _selectedDog.postValue(dogDao.getById(uuid))
        }
    }

}