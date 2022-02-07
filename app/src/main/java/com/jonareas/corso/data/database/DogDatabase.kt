package com.jonareas.corso.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jonareas.corso.data.dao.DogDao
import com.jonareas.corso.data.database.DogDatabase.Companion.DATABASE_VERSION
import com.jonareas.corso.data.model.Dog

@Database(entities = [Dog::class], version = DATABASE_VERSION, exportSchema = false)
abstract class DogDatabase : RoomDatabase() {

    abstract fun dogDao() : DogDao

    companion object {
        internal const val DATABASE_VERSION : Int = 3
        private const val DATABASE_NAME : String = "Corso.db"

         fun buildDatabase(context : Context) : DogDatabase =
            Room.databaseBuilder(context, DogDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()

    }

}