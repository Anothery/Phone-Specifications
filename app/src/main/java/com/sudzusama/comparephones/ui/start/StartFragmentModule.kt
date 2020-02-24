package com.sudzusama.comparephones.ui.start

import com.sudzusama.comparephones.di.PerFragment
import com.sudzusama.comparephones.ui.recent.RecentFragment
import com.sudzusama.comparephones.ui.recent.RecentModule
import com.sudzusama.comparephones.ui.selection.SelectionFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class StartFragmentModule {
    @PerFragment
    @ContributesAndroidInjector(modules = [RecentModule::class])
    abstract fun bindRecentFragment(): RecentFragment

    @ContributesAndroidInjector
    abstract fun bindSelectiontFragment(): SelectionFragment
}