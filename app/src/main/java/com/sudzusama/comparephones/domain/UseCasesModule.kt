package com.sudzusama.comparephones.domain

import com.sudzusama.comparephones.domain.repository.DeviceRepository
import com.sudzusama.comparephones.domain.usecase.*
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named
import javax.inject.Singleton

@Module
class UseCasesModule {
    @Provides
    @Singleton
    @Named("IO")
    fun provideIoScheduler() = Schedulers.io()

    @Provides
    @Singleton
    @Named("Main")
    fun provideMainThreadScheduler() = AndroidSchedulers.mainThread()

    @Provides
    @Singleton
    fun provideUseCaseSaveComparsion(
        repository: DeviceRepository,
        @Named("IO") subsScheduler: Scheduler,
        @Named("Main") postExecScheduler: Scheduler
    ): SaveComparsionUseCase =
        SaveComparsionUseCase(
            repository,
            subsScheduler,
            postExecScheduler
        )

    @Provides
    @Singleton
    fun provideUseCaseGetComparsionById(
        repository: DeviceRepository,
        @Named("IO") subsScheduler: Scheduler,
        @Named("Main") postExecScheduler: Scheduler
    ): GetComparsionByIdUseCase =
        GetComparsionByIdUseCase(
            repository,
            subsScheduler,
            postExecScheduler
        )


    @Singleton
    @Provides
    fun provideUseCaseRecentComparsions(
        repository: DeviceRepository,
        @Named("IO") subsScheduler: Scheduler,
        @Named("Main") postExecScheduler: Scheduler
    ) = GetRecentComparsionsUseCase(
        repository,
        subsScheduler,
        postExecScheduler
    )

    @Singleton
    @Provides
    fun provideGetDevicesByNameUseCase(
        repository: DeviceRepository,
        @Named("IO") subsScheduler: Scheduler,
        @Named("Main") postExecScheduler: Scheduler
    ) = GetDevicesByNameUseCase(
        repository,
        subsScheduler,
        postExecScheduler
    )

    @Singleton
    @Provides
    fun provideUpdateDeviceeUseCase(
        repository: DeviceRepository,
        @Named("IO") subsScheduler: Scheduler,
        @Named("Main") postExecScheduler: Scheduler
    ) = UpdateDeviceUseCase(
        repository,
        subsScheduler,
        postExecScheduler
    )

    @Singleton
    @Provides
    fun provideGetLastUsedDeviceeUseCase(
        repository: DeviceRepository,
        @Named("IO") subsScheduler: Scheduler,
        @Named("Main") postExecScheduler: Scheduler
    ) = GetRecentDevicesUseCase(
        repository,
        subsScheduler,
        postExecScheduler
    )


}