package com.sudzusama.comparephones.ui.selection

import dagger.Binds
import dagger.Module

@Module
abstract class SelectionModule {
    @Binds
    abstract fun bindView(selectionFragment: SelectionFragment): SelectionContract.View

    @Binds
    abstract fun bindPresenter(selectionPresenter: SelectionPresenter): SelectionContract.Presenter
}