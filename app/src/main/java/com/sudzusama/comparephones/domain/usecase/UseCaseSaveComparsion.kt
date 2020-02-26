package com.sudzusama.comparephones.domain.usecase

import com.sudzusama.comparephones.domain.entity.Comparsion
import com.sudzusama.comparephones.domain.repository.DeviceRepository
import io.reactivex.Flowable
import javax.inject.Inject


class UseCaseSaveComparsion @Inject constructor(private val repository: DeviceRepository) :
    UseCase<Comparsion>() {
    private lateinit var firstDeviceName: String
    private lateinit var secondDeviceName: String

    fun setComparsion(firstDeviceName: String, secondDeviceName: String) {
        this.firstDeviceName = firstDeviceName
        this.secondDeviceName = secondDeviceName
    }

    override fun createUseCase(): Flowable<Comparsion> {
        return repository.addComparsion(firstDeviceName, secondDeviceName).toFlowable()
    }
}