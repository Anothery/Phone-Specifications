package com.sudzusama.comparephones.ui.recent

import com.sudzusama.comparephones.domain.entity.Comparsion
import com.sudzusama.comparephones.domain.entity.Device
import com.sudzusama.comparephones.ui.base.MVPPresenter

interface RecentContract {
    interface View {
        fun updateRecentComparsionsList()
        fun showRecentComparsionsList()
        fun hideRecentComparsionsList()
        fun startComparingActivity(id: Int)
        fun enableRecentComparsionsList()
        fun disableRecentComparsionsList()
        fun showRecentDevicesList()
        fun hideRecentDevicesList()
        fun updateRecentDevicesList()
    }

    interface Presenter : MVPPresenter<View> {
        fun onRecentComparsionsItemClicked(comparsion: Comparsion)
        fun onViewCreated(comparsions: ArrayList<Comparsion>, devices: ArrayList<Device>)
        fun onViewResumed()
        fun onRecentDevicesItemClicked(device: Device)
    }
}