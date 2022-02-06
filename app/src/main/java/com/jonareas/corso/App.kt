package com.jonareas.corso

import android.app.Application
import com.jonareas.corso.model.database.DogDatabase

class App : Application() {

    companion object {
        private lateinit var app : Application
        val database = DogDatabase.database(app)
    }

    override fun onCreate() {
        super.onCreate()
        app = this
    }



}