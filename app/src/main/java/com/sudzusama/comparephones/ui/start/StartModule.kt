package com.sudzusama.comparephones.ui.start

import com.sudzusama.comparephones.di.ActivityScope
import dagger.Module
import dagger.Provides

@Module
class StartModule {
    @ActivityScope
    @Provides
    fun providePresenter(): StartContract.Presenter = StartPresenter()
}