package com.sudzusama.comparephones.data

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.sudzusama.comparephones.data.db.DatabaseModule
import com.sudzusama.comparephones.data.db.DevicesDatabase
import com.sudzusama.comparephones.data.db.repository.DeviceLocalRepository
import com.sudzusama.comparephones.data.network.NetworkModule
import com.sudzusama.comparephones.data.network.repository.DeviceRemoteRepository
import com.sudzusama.comparephones.domain.repositories.DeviceLocalRepo
import com.sudzusama.comparephones.domain.repositories.DeviceRepo
import com.sudzusama.comparephones.utils.DATABASE_NAME
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [NetworkModule::class, DatabaseModule::class])
class DataModule {
    @Singleton
    @Provides
    fun provideRemoteDeviceRepository(deviceRemoteRepository: DeviceRemoteRepository): DeviceRepo =
        deviceRemoteRepository

    @Singleton
    @Provides
    fun provideLocalDeviceRepository(deviceLocalRepository: DeviceLocalRepository): DeviceLocalRepo =
        deviceLocalRepository

}