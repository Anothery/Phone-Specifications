package com.sudzusama.comparephones.domain.usecase

import com.sudzusama.comparephones.domain.entity.Comparsion
import com.sudzusama.comparephones.domain.entity.Device
import com.sudzusama.comparephones.domain.repository.DeviceRepository
import io.reactivex.Flowable
import javax.inject.Inject


class UseCaseSaveComparsion @Inject constructor(private val repository: DeviceRepository) :
    UseCase<Comparsion>() {
    private lateinit var comparsion: Comparsion

    fun createComparsion(firstDevice: Device, secondDevice: Device)  {
        this.comparsion = Comparsion(0, firstDevice, secondDevice)
    }

    override fun createUseCase(): Flowable<Comparsion> {
        return repository.addComparsion(comparsion).toFlowable()
    }
}