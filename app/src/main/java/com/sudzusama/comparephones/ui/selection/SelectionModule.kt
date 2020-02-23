package com.sudzusama.comparephones.ui.selection

import com.sudzusama.comparephones.di.PerFragment
import com.sudzusama.comparephones.domain.repository.DeviceRepository
import com.sudzusama.comparephones.domain.usecase.UseCaseSaveComparsion
import dagger.Module
import dagger.Provides

@Module
class SelectionModule {
    @PerFragment
    @Provides
    fun provideView(selectionFragment: SelectionFragment): SelectionContract.View =
        selectionFragment

    @PerFragment
    @Provides
    fun providePresenter(selectionPresenter: SelectionPresenter): SelectionContract.Presenter =
        selectionPresenter

    @PerFragment
    @Provides
    fun provideSaveComparsionUseCase(repository: DeviceRepository) =
        UseCaseSaveComparsion(repository)
}