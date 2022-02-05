package com.jonareas.corso.model.dao

import androidx.room.*

@Dao
interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entity : T)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(vararg entity : T)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun update(entity : T)

    @Delete
    fun delete(entity : T)

}