package com.sudzusama.comparephones.ui.recent

import com.sudzusama.comparephones.di.FragmentScope
import com.sudzusama.comparephones.domain.repository.DeviceRepository
import com.sudzusama.comparephones.domain.usecase.UseCaseGetComparsionById
import dagger.Module
import dagger.Provides

@Module
class RecentModule {
    @FragmentScope
    @Provides
    fun provideRecentPresenter(recentPresenter: RecentPresenter): RecentContract.Presenter =
        recentPresenter

}