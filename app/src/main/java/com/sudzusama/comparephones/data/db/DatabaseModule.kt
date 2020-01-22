package com.sudzusama.comparephones.data.db

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.sudzusama.comparephones.utils.DATABASE_NAME
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideContext(application: Application): Context = application

    
    @Singleton
    @Provides
    fun provideDeviceDatabase(context: Context): DevicesDatabase =
        Room.databaseBuilder(context, DevicesDatabase::class.java, DATABASE_NAME).build()
}