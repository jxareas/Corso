package com.jonareas.corso.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.jonareas.corso.data.model.Dog

@Dao
interface DogDao : BaseDao<Dog> {

    @Query("SELECT * FROM dog ORDER BY dog_name")
    fun getAll() : List<Dog>

    @Query("SELECT * FROM dog where breed_id = :breedId")
    fun getById(breedId : Int) : Dog

    @Query("DELETE FROM dog")
    fun deleteAll()

}