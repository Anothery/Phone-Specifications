package com.sudzusama.comparephones.data.model.mapper

import com.sudzusama.comparephones.data.model.ComparsionEntity
import com.sudzusama.comparephones.data.model.ComparsionWithDevices
import com.sudzusama.comparephones.domain.entity.Comparsion
import javax.inject.Inject


class ComparsionEntityToDomainMapper @Inject constructor(
    private val deviceEntityToDomainMapper: DeviceEntityToDomainMapper
) : Mapper<ComparsionWithDevices, Comparsion>() {

    override fun map(source: ComparsionWithDevices): Comparsion {
        return Comparsion(
            source.comparsionId,
            deviceEntityToDomainMapper.map(source.firstDevice),
            deviceEntityToDomainMapper.map(source.secondDevice)
        )
    }

    override fun reverseMap(source: Comparsion): ComparsionWithDevices {
        val firstDevice = deviceEntityToDomainMapper.reverseMap(source.firstDevice)
        val secondDevice = deviceEntityToDomainMapper.reverseMap(source.secondDevice)
        return ComparsionWithDevices(
            source.comparsionId,
            firstDevice.DeviceName,
            secondDevice.DeviceName,
            firstDevice,
            secondDevice
        )
    }

    fun reverseMapToRawEntity(source: Comparsion): ComparsionEntity {
        return ComparsionEntity(
            source.comparsionId,
            source.firstDevice.DeviceName,
            source.secondDevice.DeviceName
        )
    }
}