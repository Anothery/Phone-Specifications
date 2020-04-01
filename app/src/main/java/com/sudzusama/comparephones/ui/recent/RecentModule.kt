package com.sudzusama.comparephones.ui.recent

import com.sudzusama.comparephones.di.FragmentScope
import dagger.Module
import dagger.Provides

@Module
class RecentModule {
    @FragmentScope
    @Provides
    fun provideRecentPresenter(recentPresenter: RecentPresenter): RecentContract.Presenter =
        recentPresenter

}