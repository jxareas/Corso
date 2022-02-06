package com.jonareas.corso.di

import android.content.Context
import com.jonareas.corso.data.database.DogDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    fun provideDogDao(database : DogDatabase) = database.dogDao()

    @Provides @Singleton
    fun provideDatabase(@ApplicationContext context : Context) =
        DogDatabase.buildDatabase(context)


}