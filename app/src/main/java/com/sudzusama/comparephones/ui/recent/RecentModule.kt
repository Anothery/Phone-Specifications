package com.sudzusama.comparephones.ui.recent

import dagger.Module
import dagger.Provides

@Module
class RecentModule {
    @Provides
    fun provideRecentView(recentFragment: RecentFragment) : RecentContract.View = recentFragment

    @Provides
    fun provideRecentPresenter(recentPresenter: RecentPresenter) : RecentContract.Presenter = recentPresenter

}