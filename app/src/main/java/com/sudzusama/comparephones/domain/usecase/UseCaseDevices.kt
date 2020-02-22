package com.sudzusama.comparephones.domain.usecase

import com.sudzusama.comparephones.domain.entity.Device
import com.sudzusama.comparephones.domain.repository.DeviceRepository
import io.reactivex.Single
import javax.inject.Inject

class UseCaseDevices @Inject constructor(val repository: DeviceRepository) :
    UseCase<List<Device>>() {
    private lateinit var deviceName: String

    fun searchDeviceByName(name: String) {
        deviceName = name
    }

    override fun createUseCase(): Single<List<Device>> {
        return repository.getDevices(deviceName)
    }
}