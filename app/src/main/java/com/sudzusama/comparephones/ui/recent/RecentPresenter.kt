package com.sudzusama.comparephones.ui.recent

import android.util.Log
import com.sudzusama.comparephones.domain.entity.Comparsion
import com.sudzusama.comparephones.domain.entity.Device
import com.sudzusama.comparephones.domain.usecase.GetRecentComparsionsUseCase
import com.sudzusama.comparephones.domain.usecase.GetRecentDevicesUseCase
import javax.inject.Inject

class RecentPresenter @Inject constructor(
    private val getRecentComparsionsUseCase: GetRecentComparsionsUseCase,
    private val getRecentDevicesUseCase: GetRecentDevicesUseCase
) :
    RecentContract.Presenter {

    override var view: RecentContract.View? = null

    private val RECENT_COMPARSIONS_AMOUNT = 3

    private lateinit var recentComparsions: ArrayList<Comparsion>
    private lateinit var recentDevices: ArrayList<Device>

    override fun onViewCreated(comparsions: ArrayList<Comparsion>, devices: ArrayList<Device>) {
        recentComparsions = comparsions
        recentDevices = devices
        getRecentComparsions()
        getRecentDevices()
    }

    private fun getRecentComparsions() {
        getRecentComparsionsUseCase.setComparsionAmount(RECENT_COMPARSIONS_AMOUNT)
        getRecentComparsionsUseCase.subscribe(
            this::onRecentComparsionsListArrived,
            this::onRecentComparsionsListArrivingError
        )
    }

    private fun getRecentDevices() {
        getRecentDevicesUseCase.subscribe(
            this::onRecentDevicesListArrived,
            this::onRecentDevicesListArrivingError
        )
    }

    private fun onRecentComparsionsListArrived(list: List<Comparsion>) {
        recentComparsions.clear()
        recentComparsions.addAll(list)
        if (list.isNotEmpty()) {
            view?.showRecentComparsionsList()
            view?.updateRecentComparsionsList()
        } else {
            view?.hideRecentComparsionsList()
        }
    }


    private fun onRecentComparsionsListArrivingError(t: Throwable) {
        Log.e(this.javaClass.simpleName, t.message)
    }

    private fun onRecentDevicesListArrived(list: List<Device>) {
        recentDevices.clear()
        recentDevices.addAll(list)
        if (list.isNotEmpty()) {
            view?.showRecentDevicesList()
            view?.updateRecentDevicesList()
        } else {
            view?.hideRecentDevicesList()
        }
    }

    private fun onRecentDevicesListArrivingError(t: Throwable) {
        Log.e(this.javaClass.simpleName, t.message)
    }

    override fun onRecentComparsionsItemClicked(comparsion: Comparsion) {
        view?.disableRecentComparsionsList()
        view?.startComparingActivity(comparsion.comparsionId)
        view?.enableRecentComparsionsList()
    }

    override fun onRecentDevicesItemClicked(device: Device) {
        //TODO
    }

    override fun onViewResumed() {
        getRecentComparsions()
        getRecentDevices()
    }

    override fun onDetach() {
        getRecentComparsionsUseCase.dispose()
        getRecentDevicesUseCase.dispose()
        super.onDetach()
    }
}