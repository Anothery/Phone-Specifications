package com.sudzusama.comparephones.domain.usecase

import com.sudzusama.comparephones.domain.entity.Comparsion
import com.sudzusama.comparephones.domain.repository.DeviceRepository
import io.reactivex.Flowable
import javax.inject.Inject


class UseCaseRecentComparsions @Inject constructor(val repository: DeviceRepository) :
    UseCase<List<Comparsion>>() {
    private var comparsionAmount = 0

    fun setComparsionAmount(amount: Int) {
        comparsionAmount = amount
    }

    override fun createUseCase(): Flowable<List<Comparsion>> {
        return repository.getLatestComparsions(comparsionAmount).toFlowable()
    }
}