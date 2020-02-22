package com.sudzusama.comparephones.domain.usecase

import com.sudzusama.comparephones.domain.entity.Comparsion
import com.sudzusama.comparephones.domain.repository.DeviceRepository
import io.reactivex.Flowable
import javax.inject.Inject


class UseCaseSaveComparsion @Inject constructor(val repository: DeviceRepository) :
    UseCase<Comparsion>() {
    private lateinit var comparsion: Comparsion

    fun setComparsion(comparsion: Comparsion) {
        this.comparsion = comparsion
    }

    override fun createUseCase(): Flowable<Comparsion> {
        return repository.addComparsion(comparsion).toFlowable()
    }
}