package com.sudzusama.comparephones.ui.recent

import android.util.Log
import com.sudzusama.comparephones.domain.entity.Comparsion
import com.sudzusama.comparephones.domain.entity.Device
import com.sudzusama.comparephones.domain.usecase.UseCaseRecentComparsions
import javax.inject.Inject

class RecentPresenter @Inject constructor(
    private val view: RecentContract.View,
    private val recentComparsionsUseCase: UseCaseRecentComparsions
) :
    RecentContract.Presenter {
    private val RECENT_COMPARSIONS_AMOUNT = 3

    private lateinit var recentComparsions: ArrayList<Comparsion>
    private lateinit var recentDevices: ArrayList<Device>

    override fun onViewCreated(comparsions: ArrayList<Comparsion>, devices: ArrayList<Device>) {
        recentComparsions = comparsions
        recentDevices = devices
        getRecentComparsions()

    }

    private fun getRecentComparsions() {
        recentComparsionsUseCase.setComparsionAmount(RECENT_COMPARSIONS_AMOUNT)
        recentComparsionsUseCase.subscribe(
            this::onRecentComparsionsListArrived,
            this::onRecentComparsionsListArrivingError
        )
    }

    private fun onRecentComparsionsListArrived(list: List<Comparsion>) {
        recentComparsions.clear()
        recentComparsions.addAll(list)
        if (list.isNotEmpty()) {
            view.showRecentComparsionsList()
            view.updateRecentComparsionsList()
        } else {
            view.hideRecentComparsionsList()
        }
    }

    private fun onRecentComparsionsListArrivingError(t: Throwable) {
        Log.e(this.javaClass.simpleName, t.message)
    }

    override fun onRecentComparsionsItemClicked(comparsion: Comparsion) {
        view.startComparingActivity(comparsion.comparsionId)
    }

    override fun onViewResumed() {
        getRecentComparsions()
    }
}