package com.sudzusama.comparephones.ui.selection

import com.sudzusama.comparephones.domain.repository.DeviceRepository
import com.sudzusama.comparephones.domain.usecase.UseCaseGetDeviceByName
import com.sudzusama.comparephones.domain.usecase.UseCaseSaveComparsion
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SelectionModule {
    @Provides
    @Singleton
    fun providePresenter(selectionPresenter: SelectionPresenter): SelectionContract.Presenter =
        selectionPresenter

    @Provides
    fun provideSaveComparsionUseCase(repository: DeviceRepository) =
        UseCaseSaveComparsion(repository)


}