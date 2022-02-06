package com.jonareas.corso.data.dao

import androidx.room.*

@Dao
interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entity : T)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(vararg entity : T) : List<Long>

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun update(entity : T)

    @Delete
    fun delete(entity : T)

}