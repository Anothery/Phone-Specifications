package com.sudzusama.comparephones.ui.start

import com.sudzusama.comparephones.di.PerActivity
import dagger.Binds
import dagger.Module

@Module
abstract class StartModule {
    @PerActivity
    @Binds
    abstract fun bindView(startActivity: StartActivity): StartContract.View

    @PerActivity
    @Binds
    abstract fun bindPresenter(startPresenter: StartPresenter): StartContract.Presenter

}