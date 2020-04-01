package com.sudzusama.comparephones.ui.comparing

import com.sudzusama.comparephones.di.ActivityScope
import dagger.Module
import dagger.Provides

@Module
class ComparingModule {
    @ActivityScope
    @Provides
    fun providePresenter(comparingPresenter: ComparingPresenter): ComparingContract.Presenter =
        comparingPresenter

}