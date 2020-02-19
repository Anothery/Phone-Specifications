package com.sudzusama.comparephones.data

import com.sudzusama.comparephones.data.model.mapper.MapperModule
import com.sudzusama.comparephones.data.repository.DeviceDataRepository
import com.sudzusama.comparephones.data.source.db.DatabaseModule
import com.sudzusama.comparephones.data.source.network.NetworkModule
import com.sudzusama.comparephones.domain.repositories.DeviceRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [NetworkModule::class, DatabaseModule::class, MapperModule::class])
class DataModule {
    @Singleton
    @Provides
    fun provideDeviceRepository(deviceDataRepository: DeviceDataRepository): DeviceRepository =
        deviceDataRepository
}