package com.sudzusama.comparephones.domain.usecase

import com.sudzusama.comparephones.domain.entity.Comparsion
import com.sudzusama.comparephones.domain.repository.DeviceRepository
import io.reactivex.Flowable
import javax.inject.Inject

class UseCaseGetComparsionById @Inject constructor(private val repository: DeviceRepository) :
    UseCase<Comparsion>() {
    private var comparsionId = 0

    fun setComparsionId(id: Int) {
        comparsionId = id
    }

    override fun createUseCase(): Flowable<Comparsion> {
        return repository.getComparsionById(comparsionId).toFlowable()
    }
}