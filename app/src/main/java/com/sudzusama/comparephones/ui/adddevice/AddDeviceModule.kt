package com.sudzusama.comparephones.ui.adddevice

import com.sudzusama.comparephones.di.PerActivity
import com.sudzusama.comparephones.domain.repository.DeviceRepository
import com.sudzusama.comparephones.domain.usecase.UseCaseDevices
import com.sudzusama.comparephones.domain.usecase.UseCaseSaveComparsion
import dagger.Module
import dagger.Provides

@Module
class AddDeviceModule {
    @Provides
    fun provideView(addDeviceActivity: AddDeviceActivity): AddDeviceContract.View =
        addDeviceActivity

    @Provides
    fun bindPresenter(addDevicePresenter: AddDevicePresenter): AddDeviceContract.Presenter =
        addDevicePresenter

    @Provides
    fun provideGetDevicesByNameUseCase(repository: DeviceRepository) =
        UseCaseDevices(repository)
}