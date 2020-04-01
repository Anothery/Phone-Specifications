package com.sudzusama.comparephones.domain.usecase

import com.sudzusama.comparephones.domain.entity.Device
import com.sudzusama.comparephones.domain.repository.DeviceRepository
import io.reactivex.Flowable
import io.reactivex.Scheduler
import javax.inject.Inject


class GetDeviceByNameUseCase @Inject constructor(
    private val repository: DeviceRepository, subscribeScheduler: Scheduler,
    postExecutionScheduler: Scheduler
) :
    UseCase<Device>(subscribeScheduler, postExecutionScheduler) {
    private lateinit var deviceName: String

    fun setDeviceName(name: String) {
        deviceName = name
    }

    override fun createUseCase(): Flowable<Device> {
        return repository.getDeviceByName(deviceName).toFlowable()
    }
}