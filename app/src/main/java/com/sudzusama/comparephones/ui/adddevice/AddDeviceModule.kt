package com.sudzusama.comparephones.ui.adddevice

import dagger.Binds
import dagger.Module

@Module
abstract class AddDeviceModule {
    @Binds
    abstract fun bindView(addDeviceActivity: AddDeviceActivity): AddDeviceContract.View

    @Binds
    abstract fun bindPresenter(addDevicePresenter: AddDevicePresenter): AddDeviceContract.Presenter
}