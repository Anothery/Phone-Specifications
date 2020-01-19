package com.sudzusama.comparephones.ui.comparing

import com.sudzusama.comparephones.ui.device.DeviceFragment
import com.sudzusama.comparephones.ui.device.DeviceModule
import com.sudzusama.comparephones.di.FragmentScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ComparingFragmentModule {
    @FragmentScope
    @ContributesAndroidInjector(modules = [DeviceModule::class])
    abstract fun provideDeviceFragment(): DeviceFragment
}