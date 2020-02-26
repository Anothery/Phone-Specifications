package com.sudzusama.comparephones.ui.start

import com.sudzusama.comparephones.di.FragmentScope
import com.sudzusama.comparephones.ui.recent.RecentFragment
import com.sudzusama.comparephones.ui.recent.RecentModule
import com.sudzusama.comparephones.ui.selection.SelectionFragment
import com.sudzusama.comparephones.ui.selection.SelectionModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class StartFragmentModule {
    @FragmentScope
    @ContributesAndroidInjector(modules = [RecentModule::class])
    abstract fun bindRecentFragment(): RecentFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [SelectionModule::class])
    abstract fun bindSelectiontFragment(): SelectionFragment
}