package com.sudzusama.comparephones.ui.addDevice

import com.sudzusama.comparephones.data.model.DeviceInfo
import io.reactivex.Observable
import java.util.*

interface AddDevice {
    interface View {
        fun updateRecyclerView()
        fun disableMatchesCount()
        fun enableMatchesCount()
        fun setMatchesCount(matchesCount : Int)
        fun finishActivity(result: DeviceInfo)
    }

    interface Presenter {
        fun onCreate(matches : ArrayList<DeviceInfo>)
        fun observeFromText(observable: Observable<CharSequence>)
        fun onDeviceItemClicked(device : DeviceInfo)
        fun onStop()
    }
}