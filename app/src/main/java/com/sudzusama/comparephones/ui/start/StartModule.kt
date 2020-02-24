package com.sudzusama.comparephones.ui.start

import com.sudzusama.comparephones.di.PerActivity
import com.sudzusama.comparephones.ui.selection.SelectionFragment
import com.sudzusama.comparephones.ui.selection.SelectionPresenter
import dagger.Module
import dagger.Provides

@Module
class StartModule {
    @PerActivity
    @Provides
    fun bindView(startActivity: StartActivity): StartContract.View = startActivity

    @PerActivity
    @Provides
    fun bindPresenter(startPresenter: StartPresenter): StartContract.Presenter = startPresenter

}