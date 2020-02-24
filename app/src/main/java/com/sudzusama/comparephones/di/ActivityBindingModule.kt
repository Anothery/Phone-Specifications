package com.sudzusama.comparephones.di

import com.sudzusama.comparephones.ui.adddevice.AddDeviceActivity
import com.sudzusama.comparephones.ui.adddevice.AddDeviceModule
import com.sudzusama.comparephones.ui.comparing.ComparingActivity
import com.sudzusama.comparephones.ui.comparing.ComparingModule
import com.sudzusama.comparephones.ui.start.StartActivity
import com.sudzusama.comparephones.ui.start.StartFragmentModule
import com.sudzusama.comparephones.ui.start.StartModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @PerActivity
    @ContributesAndroidInjector(modules = [AddDeviceModule::class])
    abstract fun bindAddDeviceActivity(): AddDeviceActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [ComparingModule::class])
    abstract fun bindComparingActivity(): ComparingActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [StartModule::class, StartFragmentModule::class])
    abstract fun bindStartActivity(): StartActivity
}