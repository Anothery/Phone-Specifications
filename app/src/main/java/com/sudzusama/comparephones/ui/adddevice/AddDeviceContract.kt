package com.sudzusama.comparephones.ui.adddevice

import com.sudzusama.comparephones.domain.entities.Device
import io.reactivex.Observable
import java.util.*

interface AddDeviceContract {
    interface View {
        fun updateRecyclerView()
        fun disableMatchesCount()
        fun enableMatchesCount()
        fun setMatchesCount(matchesCount : Int)
        fun finishActivity(result: Device)
    }

    interface Presenter {
        fun onCreate(matches : ArrayList<Device>)
        fun observeFromText(observable: Observable<CharSequence>)
        fun onDeviceItemClicked(device : Device)
        fun onDestroy()
    }
}