package com.sudzusama.comparephones.domain.usecase

import com.sudzusama.comparephones.domain.entity.Device
import com.sudzusama.comparephones.domain.repository.DeviceRepository
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class UseCaseDevices @Inject constructor(private val repository: DeviceRepository) :
    UseCase<List<Device>>() {
    private lateinit var deviceName: String

    fun searchDeviceByName(name: String) {
        deviceName = name
    }

    override fun createUseCase(): Flowable<List<Device>> {
        return repository.getDevices(deviceName).toFlowable()
    }
}