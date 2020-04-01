package com.sudzusama.comparephones.domain.usecase

import com.sudzusama.comparephones.domain.entity.Comparsion
import com.sudzusama.comparephones.domain.repository.DeviceRepository
import io.reactivex.Flowable
import io.reactivex.Scheduler
import javax.inject.Inject


class GetRecentComparsionsUseCase @Inject constructor(
    private val repository: DeviceRepository,
    subscribeScheduler: Scheduler,
    postExecutionScheduler: Scheduler
) :
    UseCase<List<Comparsion>>(subscribeScheduler, postExecutionScheduler) {
    private var comparsionAmount = 1

    fun setComparsionAmount(amount: Int) {
        comparsionAmount = amount
    }

    override fun createUseCase(): Flowable<List<Comparsion>> {
        return repository.getLatestComparsions(comparsionAmount).toFlowable()
    }
}