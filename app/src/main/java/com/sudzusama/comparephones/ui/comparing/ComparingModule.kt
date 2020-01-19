package com.sudzusama.comparephones.ui.comparing

import com.sudzusama.comparephones.di.ActivityScope
import dagger.Binds
import dagger.Module

@Module
abstract class ComparingModule {
    @ActivityScope
    @Binds
    abstract fun bindView(comparingActivity: ComparingActivity): Comparing.View

    @ActivityScope
    @Binds
    abstract fun bindPresenter(comparingPresenter: ComparingPresenter): Comparing.Presenter
}