package com.sudzusama.comparephones.data.model.mapper

import com.sudzusama.comparephones.data.model.Device
import javax.inject.Inject
import com.sudzusama.comparephones.domain.entities.Device as DeviceDomain

class DeviceListMapper @Inject constructor(private val deviceMapper:  Mapper<Device, DeviceDomain>) :
    Mapper<List<Device>, List<DeviceDomain>> {
    override fun map(source: List<Device>): List<DeviceDomain> {
        return source.map { deviceMapper.map(it) }
    }
}