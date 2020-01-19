package com.sudzusama.comparephones.ui.addDevice

import com.sudzusama.comparephones.data.model.Device
import io.reactivex.Observable
import java.util.*

interface AddDevice {
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
        fun onStop()
    }
}