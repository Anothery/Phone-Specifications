package com.sudzusama.comparephones.data.model.mapper

import com.sudzusama.comparephones.data.model.ComparsionWithDevices
import com.sudzusama.comparephones.data.model.Device
import javax.inject.Inject
import com.sudzusama.comparephones.domain.entities.Comparsion as ComparsionDomain
import com.sudzusama.comparephones.domain.entities.Device as DeviceDomain

class ComparsionListMapper @Inject constructor(private val deviceMapper: Mapper<Device, DeviceDomain>) :
    Mapper<List<ComparsionWithDevices>, List<ComparsionDomain>> {

    override fun map(source: List<ComparsionWithDevices>): List<ComparsionDomain> {
        return source.map { map(it) }
    }

    private fun map(source: ComparsionWithDevices): ComparsionDomain {
        return ComparsionDomain(
            deviceMapper.map(source.firstDevice),
            deviceMapper.map(source.secondDevice)
        )
    }
}