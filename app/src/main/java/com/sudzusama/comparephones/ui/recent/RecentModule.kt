package com.sudzusama.comparephones.ui.recent

import com.sudzusama.comparephones.domain.repository.DeviceRepository
import com.sudzusama.comparephones.domain.usecase.UseCaseGetComparsionById
import dagger.Module
import dagger.Provides

@Module
class RecentModule {
    @Provides
    fun provideRecentView(recentFragment: RecentFragment): RecentContract.View = recentFragment

    @Provides
    fun provideRecentPresenter(recentPresenter: RecentPresenter): RecentContract.Presenter =
        recentPresenter

    @Provides
    fun provideUseCaseGetComparsionById(repository: DeviceRepository): UseCaseGetComparsionById =
        UseCaseGetComparsionById(repository)

}