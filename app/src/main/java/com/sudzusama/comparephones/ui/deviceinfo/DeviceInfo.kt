package com.sudzusama.comparephones.ui.deviceinfo

import com.sudzusama.comparephones.data.model.Device
import com.sudzusama.comparephones.data.model.Specification

interface DeviceInfo {
    interface View {

        fun updateRecyclerView()
    }
    interface Presenter {
        fun onCreate(specifications: ArrayList<Specification>, device: Device)
    }
}