package com.sudzusama.comparephones.ui.comparing

import dagger.Binds
import dagger.Module

@Module
abstract class ComparingModule {
    @Binds
    abstract fun bindView(comparingActivity: ComparingActivity): ComparingContract.View

    @Binds
    abstract fun bindPresenter(comparingPresenter: ComparingPresenter): ComparingContract.Presenter
}