package com.sudzusama.comparephones.ui.deviceinfo

import com.sudzusama.comparephones.di.FragmentScope
import dagger.Binds
import dagger.Module

@Module
abstract class DeviceInfoModule {
    @FragmentScope
    @Binds
    abstract fun bindView(deviceInfoFragment: DeviceInfoFragment): DeviceInfo.View

    @FragmentScope
    @Binds
    abstract fun bindPresenter(deviceInfoPresenter: DeviceInfoPresenter): DeviceInfo.Presenter

}