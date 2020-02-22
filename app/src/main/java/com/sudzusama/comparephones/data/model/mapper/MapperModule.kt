package com.sudzusama.comparephones.data.model.mapper

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MapperModule {
    @Singleton
    @Provides
    fun provideDeviceEntityToDomainMapper() = DeviceEntityToDomainMapper()

    @Singleton
    @Provides
    fun provideComparsionEntityToDomainMepper(deviceEntityToDomainMapper: DeviceEntityToDomainMapper) =
        ComparsionEntityToDomainMapper(deviceEntityToDomainMapper)
}