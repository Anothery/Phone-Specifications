package com.sudzusama.comparephones.data.repository

import com.sudzusama.comparephones.data.model.ComparsionEntity
import com.sudzusama.comparephones.data.model.DeviceEntity
import com.sudzusama.comparephones.data.model.mapper.ComparsionEntityToDomainMapper
import com.sudzusama.comparephones.data.model.mapper.DeviceEntityToDomainMapper
import com.sudzusama.comparephones.data.source.db.DevicesDatabase
import com.sudzusama.comparephones.data.source.network.CPApiService
import com.sudzusama.comparephones.domain.entity.Comparsion
import com.sudzusama.comparephones.domain.entity.Device
import com.sudzusama.comparephones.domain.repository.DeviceRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class DeviceDataRepository @Inject constructor(
    private val db: DevicesDatabase,
    private val devicesApi: CPApiService,
    private val deviceEntityToDomainMapper: DeviceEntityToDomainMapper,
    private val comparsionEntityToDomainMapper: ComparsionEntityToDomainMapper
) : DeviceRepository {

    override fun getDevices(deviceName: String): Single<List<Device>> {
        return devicesApi.getDevices(deviceName)
            .doOnSuccess { addDevices(it) }
            .map { deviceEntityToDomainMapper.map(it) }
    }

    override fun getLatestDevices(amount: Int): Single<List<Device>> {
        return db.devicesDao()
            .getLatestDevices(amount)
            .map { deviceEntityToDomainMapper.map(it) }
    }

    override fun getDeviceByName(deviceName: String): Single<Device> {
        return db.devicesDao().getDeviceByName(deviceName)
            .map { deviceEntityToDomainMapper.map(it) }
    }

    override fun getLatestComparsions(amount: Int): Single<List<Comparsion>> {
        return db.comparsionsDao().getLatestComparsions(amount)
            .map { comparsionEntityToDomainMapper.map(it) }
    }

    override fun addComparsion(firstDeviceName: String, secondDeviceName: String): Completable {
        return db.comparsionsDao()
            .insertComparsion(ComparsionEntity(0, firstDeviceName, secondDeviceName))
    }

    override fun getComparsionById(id: Int): Single<Comparsion> {
        return db.comparsionsDao().getComparsionById(id)
            .map { comparsionEntityToDomainMapper.map(it) }
    }

    private fun addDevices(devices: List<DeviceEntity>) {
        db.devicesDao().insertDevices(devices)
    }
}