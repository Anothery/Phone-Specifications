package com.sudzusama.comparephones.ui.deviceinfo

import dagger.Binds
import dagger.Module

@Module
abstract class DeviceInfoModule {
    @Binds
    abstract fun bindView(deviceInfoFragment: DeviceInfoFragment): DeviceInfoContract.View

    @Binds
    abstract fun bindPresenter(deviceInfoPresenter: DeviceInfoPresenter): DeviceInfoContract.Presenter

}