package com.sudzusama.comparephones.ui.selection

import com.sudzusama.comparephones.di.FragmentScope
import dagger.Module
import dagger.Provides

@Module
class SelectionModule {
    @FragmentScope
    @Provides
    fun providePresenter(selectionPresenter: SelectionPresenter): SelectionContract.Presenter =
        selectionPresenter
}