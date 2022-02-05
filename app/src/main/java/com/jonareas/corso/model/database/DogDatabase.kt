package com.jonareas.corso.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jonareas.corso.model.dao.DogDao
import com.jonareas.corso.model.entity.Dog

@Database(entities = [Dog::class], version = DogDatabase.DATABASE_VERSION, exportSchema = false)
abstract class DogDatabase : RoomDatabase() {

    abstract fun dogDao() : DogDao

    companion object {
        internal const val DATABASE_VERSION : Int = 10
        private const val DATABASE_NAME : String = "Corso"
        private var INSTANCE : DogDatabase? = null
        private val LOCK = Any()

        private fun buildDatabase(context : Context) : DogDatabase =
            Room.databaseBuilder(context, DogDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()

        fun database(context : Context) : DogDatabase =
            INSTANCE ?: synchronized(LOCK) {
                INSTANCE ?: buildDatabase(context)
            }.also { database -> INSTANCE = database }



    }

}