package com.sudzusama.comparephones.ui.addDevice

import com.sudzusama.comparephones.di.ActivityScope
import dagger.Binds
import dagger.Module

@Module
abstract class AddDeviceModule {
    @ActivityScope
    @Binds
    abstract fun bindView(addDeviceActivity: AddDeviceActivity): AddDevice.View

    @ActivityScope
    @Binds
    abstract fun bindPresenter(addDevicePresenter: AddDevicePresenter): AddDevice.Presenter
}