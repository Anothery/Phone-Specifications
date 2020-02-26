package com.sudzusama.comparephones.domain.usecase

import com.sudzusama.comparephones.domain.entity.Device
import com.sudzusama.comparephones.domain.repository.DeviceRepository
import io.reactivex.Flowable
import io.reactivex.Scheduler
import javax.inject.Inject

class UseCaseDevices @Inject constructor(
    private val repository: DeviceRepository, subscribeScheduler: Scheduler,
    postExecutionScheduler: Scheduler
) :
    UseCase<List<Device>>(subscribeScheduler, postExecutionScheduler) {
    private lateinit var deviceName: String

    fun searchDeviceByName(name: String) {
        deviceName = name
    }

    override fun createUseCase(): Flowable<List<Device>> {
        return repository.getDevices(deviceName).toFlowable()
    }
}