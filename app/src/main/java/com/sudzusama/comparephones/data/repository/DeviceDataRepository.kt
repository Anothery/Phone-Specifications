package com.sudzusama.comparephones.data.repository

import com.sudzusama.comparephones.data.model.ComparsionWithDevices
import com.sudzusama.comparephones.data.model.Device
import com.sudzusama.comparephones.data.model.mapper.Mapper
import com.sudzusama.comparephones.data.source.db.DevicesDatabase
import com.sudzusama.comparephones.data.source.network.CPApiService
import com.sudzusama.comparephones.domain.repositories.DeviceRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import com.sudzusama.comparephones.domain.entities.Comparsion as ComparsionDomain
import com.sudzusama.comparephones.domain.entities.Device as DeviceDomain

class DeviceDataRepository @Inject constructor(
    private val db: DevicesDatabase,
    private val devicesApi: CPApiService,
    private val deviceListMapper: Mapper<List<Device>, List<DeviceDomain>>,
    private val comparsionListMapper: Mapper<List<ComparsionWithDevices>, List<ComparsionDomain>>
) : DeviceRepository {

    override fun getDevices(deviceName: String): Single<List<DeviceDomain>> {
        return devicesApi.getDevices(deviceName)
            .doAfterSuccess { addDevices(it) }
            .map { deviceListMapper.map(it) }
    }

    override fun getLatestDevices(amount: Int): Single<List<Device>> {
        return db.devicesDao().getLatestDevices(20)
    }

    override fun getLatestComparsions(amount: Int): Single<List<ComparsionDomain>> {
        return db.comparsionsDao().getLatestComparsions(amount)
            .map { comparsionListMapper.map(it) }
    }

    private fun addDevices(devices: List<Device>) {
        db.devicesDao().insertDevices(devices)
    }


    private fun removeDevice(device: Device) {
        db.devicesDao().deleteDevice(device)
    }
}