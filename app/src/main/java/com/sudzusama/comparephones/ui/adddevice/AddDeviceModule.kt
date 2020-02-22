package com.sudzusama.comparephones.ui.adddevice

import com.sudzusama.comparephones.di.PerActivity
import com.sudzusama.comparephones.domain.repository.DeviceRepository
import com.sudzusama.comparephones.domain.usecase.UseCaseDevices
import dagger.Module
import dagger.Provides

@Module
class AddDeviceModule {
    @PerActivity
    @Provides
    fun provideView(addDeviceActivity: AddDeviceActivity): AddDeviceContract.View =
        addDeviceActivity

    @PerActivity
    @Provides
    fun bindPresenter(addDevicePresenter: AddDevicePresenter): AddDeviceContract.Presenter =
        addDevicePresenter

    @PerActivity
    @Provides
    fun provideGetDevicesByNameUseCase(repository: DeviceRepository) =
        UseCaseDevices(repository)
}