package com.sudzusama.comparephones.di

import android.app.Application
import com.sudzusama.comparephones.CPApp
import com.sudzusama.comparephones.data.DataModule
import com.sudzusama.comparephones.data.source.network.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, NetworkModule::class, ActivityBuilder::class, DataModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(application: CPApp)
}