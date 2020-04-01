package com.sudzusama.comparephones.ui.adddevice

import com.sudzusama.comparephones.di.ActivityScope
import dagger.Module
import dagger.Provides

@Module
class AddDeviceModule {
    @ActivityScope
    @Provides
    fun providePresenter(addDevicePresenter: AddDevicePresenter): AddDeviceContract.Presenter =
        addDevicePresenter
}