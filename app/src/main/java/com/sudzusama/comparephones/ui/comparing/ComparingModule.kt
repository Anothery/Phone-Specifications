package com.sudzusama.comparephones.ui.comparing

import com.sudzusama.comparephones.di.PerActivity
import com.sudzusama.comparephones.domain.repository.DeviceRepository
import com.sudzusama.comparephones.domain.usecase.UseCaseRecentComparsions
import dagger.Module
import dagger.Provides

@Module
class ComparingModule {
    @PerActivity
    @Provides
    fun provideView(comparingActivity: ComparingActivity): ComparingContract.View =
        comparingActivity

    @PerActivity
    @Provides
    fun providePresenter(comparingPresenter: ComparingPresenter): ComparingContract.Presenter =
        comparingPresenter

    @PerActivity
    @Provides
    fun provideUseCaseRecentComparsions(repository: DeviceRepository) =
        UseCaseRecentComparsions(repository)
}