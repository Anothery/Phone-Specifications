package com.sudzusama.comparephones.ui.adddevice

import com.sudzusama.comparephones.domain.entity.Device
import com.sudzusama.comparephones.ui.base.MVPPresenter
import io.reactivex.Observable
import java.util.*

interface AddDeviceContract {
    interface View {
        fun updateRecyclerView()
        fun disableMatchesCount()
        fun enableMatchesCount()
        fun setMatchesCount(matchesCount: Int)
        fun finishActivity(deviceName: String)
    }

    interface Presenter : MVPPresenter<View> {
        fun onCreate(matches: ArrayList<Device>)
        fun observeFromText(observable: Observable<CharSequence>)
        fun onDeviceItemClicked(device: Device)
    }
}