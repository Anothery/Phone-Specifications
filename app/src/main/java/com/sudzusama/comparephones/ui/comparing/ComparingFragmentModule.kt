package com.sudzusama.comparephones.ui.comparing

import com.sudzusama.comparephones.ui.deviceinfo.DeviceInfoFragment
import com.sudzusama.comparephones.ui.deviceinfo.DeviceInfoModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ComparingFragmentModule {
    @ContributesAndroidInjector(modules = [DeviceInfoModule::class])
    abstract fun bindDeviceFragment(): DeviceInfoFragment
}