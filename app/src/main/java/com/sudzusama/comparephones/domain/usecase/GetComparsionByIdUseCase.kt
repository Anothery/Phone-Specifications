package com.sudzusama.comparephones.domain.usecase

import com.sudzusama.comparephones.domain.entity.Comparsion
import com.sudzusama.comparephones.domain.repository.DeviceRepository
import io.reactivex.Flowable
import io.reactivex.Scheduler
import javax.inject.Inject

class GetComparsionByIdUseCase @Inject constructor(
    private val repository: DeviceRepository,
    subscribeScheduler: Scheduler,
    postExecutionScheduler: Scheduler
) : UseCase<Comparsion>(subscribeScheduler, postExecutionScheduler) {

    private var comparsionId = 0

    fun setComparsionId(id: Int) {
        comparsionId = id
    }

    override fun createUseCase(): Flowable<Comparsion> {
        return repository.getComparsionById(comparsionId).toFlowable()
    }
}