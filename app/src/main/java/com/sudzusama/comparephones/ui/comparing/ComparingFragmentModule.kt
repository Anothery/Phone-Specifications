package com.sudzusama.comparephones.ui.comparing

import com.sudzusama.comparephones.ui.deviceinfo.DeviceInfoFragment
import com.sudzusama.comparephones.ui.deviceinfo.DeviceInfoModule
import com.sudzusama.comparephones.di.FragmentScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ComparingFragmentModule {
    @FragmentScope
    @ContributesAndroidInjector(modules = [DeviceInfoModule::class])
    abstract fun provideDeviceFragment(): DeviceInfoFragment
}