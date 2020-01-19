package com.sudzusama.comparephones.di

import com.sudzusama.comparephones.ui.addDevice.AddDeviceActivity
import com.sudzusama.comparephones.ui.addDevice.AddDeviceModule
import com.sudzusama.comparephones.ui.comparing.ComparingActivity
import com.sudzusama.comparephones.ui.comparing.ComparingFragmentModule
import com.sudzusama.comparephones.ui.comparing.ComparingModule
import com.sudzusama.comparephones.ui.main.MainActivity
import com.sudzusama.comparephones.ui.main.MainModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {
    @ActivityScope
    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun bindMainActivity(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [AddDeviceModule::class])
    abstract fun bindAddDeviceActivity(): AddDeviceActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [ComparingModule::class, ComparingFragmentModule::class])
    abstract fun bindComparingActivity(): ComparingActivity
}