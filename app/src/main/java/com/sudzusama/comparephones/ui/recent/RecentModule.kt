package com.sudzusama.comparephones.ui.recent

import dagger.Binds
import dagger.Module

@Module
abstract class RecentModule {
    @Binds
    abstract fun provideRecentView(recentFragment: RecentFragment) : RecentContract.View

    @Binds
    abstract fun provideRecentPresenter(recentPresenter: RecentPresenter) : RecentContract.Presenter

}