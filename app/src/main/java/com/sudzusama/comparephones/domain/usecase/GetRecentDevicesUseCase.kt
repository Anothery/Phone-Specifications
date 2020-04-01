package com.sudzusama.comparephones.domain.usecase

import com.sudzusama.comparephones.domain.entity.Device
import com.sudzusama.comparephones.domain.repository.DeviceRepository
import io.reactivex.Flowable
import io.reactivex.Scheduler
import javax.inject.Inject

class GetRecentDevicesUseCase @Inject constructor(
    private val repository: DeviceRepository, subscribeScheduler: Scheduler,
    postExecutionScheduler: Scheduler
) :
    UseCase<List<Device>>(subscribeScheduler, postExecutionScheduler) {
    private val devicesAmount = 3

    override fun createUseCase(): Flowable<List<Device>> {
        return repository.getLatestDevices(devicesAmount).toFlowable()
    }
}