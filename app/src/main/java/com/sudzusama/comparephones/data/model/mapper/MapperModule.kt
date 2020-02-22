package com.sudzusama.comparephones.data.model.mapper

import com.sudzusama.comparephones.data.model.ComparsionWithDevices
import com.sudzusama.comparephones.data.model.Device
import com.sudzusama.comparephones.domain.entity.Comparsion
import com.sudzusama.comparephones.domain.entity.Device as DeviceDomain
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MapperModule {
    @Singleton
    @Provides
    fun provideDeviceListMapper(deviceListMapper: DeviceListMapper): Mapper<List<Device>, List<DeviceDomain>> =
        deviceListMapper

    @Singleton
    @Provides
    fun provideDeviceMapper(): Mapper<Device, DeviceDomain> = DeviceMapper()

    @Singleton
    @Provides
    fun provideComparsionListMapper(comparsionListMapper: ComparsionListMapper): Mapper<List<ComparsionWithDevices>, List<Comparsion>> =
        comparsionListMapper
}