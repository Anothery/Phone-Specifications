package com.sudzusama.comparephones.domain.usecase

import com.sudzusama.comparephones.domain.entity.Device
import com.sudzusama.comparephones.domain.repository.DeviceRepository
import io.reactivex.Flowable
import javax.inject.Inject


class UseCaseGetDeviceByName @Inject constructor(private val repository: DeviceRepository) :
    UseCase<Device>() {
    private lateinit var deviceName: String

    fun setDeviceName(name: String) {
        deviceName = name
    }

    override fun createUseCase(): Flowable<Device> {
        return repository.getDeviceByName(deviceName).toFlowable()
    }
}