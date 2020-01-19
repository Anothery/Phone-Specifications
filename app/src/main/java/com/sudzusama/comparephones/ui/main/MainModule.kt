package com.sudzusama.comparephones.ui.main

import com.sudzusama.comparephones.di.ActivityScope
import dagger.Binds
import dagger.Module

@Module
abstract class MainModule {
    @ActivityScope
    @Binds
    abstract fun bindView(mainActivity: MainActivity): Main.View

    @ActivityScope
    @Binds
    abstract fun bindPresenter(mainPresenter: MainPresenter): Main.Presenter
}