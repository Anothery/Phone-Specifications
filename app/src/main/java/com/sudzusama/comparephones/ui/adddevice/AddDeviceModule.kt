package com.sudzusama.comparephones.ui.adddevice

import com.sudzusama.comparephones.di.ActivityScope
import com.sudzusama.comparephones.domain.repository.DeviceRepository
import com.sudzusama.comparephones.domain.usecase.UseCaseDevices
import dagger.Module
import dagger.Provides

@Module
class AddDeviceModule {
    @ActivityScope
    @Provides
    fun providePresenter(addDevicePresenter: AddDevicePresenter): AddDeviceContract.Presenter =
        addDevicePresenter
}