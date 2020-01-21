package com.sudzusama.comparephones.data

import com.sudzusama.comparephones.data.network.NetworkModule
import com.sudzusama.comparephones.data.repository.DeviceRemoteRepository
import com.sudzusama.comparephones.domain.repositories.DeviceRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
abstract class DataModule {
    @Singleton
    @Binds
    abstract fun provideDeviceRepository(deviceRemoteRepository: DeviceRemoteRepository) : DeviceRepository
}