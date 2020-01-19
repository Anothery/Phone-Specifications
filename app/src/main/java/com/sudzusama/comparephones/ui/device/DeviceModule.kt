package com.sudzusama.comparephones.ui.device

import com.sudzusama.comparephones.di.FragmentScope
import dagger.Binds
import dagger.Module

@Module
abstract class DeviceModule {
    @FragmentScope
    @Binds
    abstract fun bindView(deviceFragment: DeviceFragment): Device.View

    @FragmentScope
    @Binds
    abstract fun bindPresenter(devicePresenter: DevicePresenter): Device.Presenter

}