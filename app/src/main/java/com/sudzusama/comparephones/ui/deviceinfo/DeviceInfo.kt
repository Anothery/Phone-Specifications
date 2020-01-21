package com.sudzusama.comparephones.ui.deviceinfo

import com.sudzusama.comparephones.domain.entities.Device
import com.sudzusama.comparephones.domain.entities.Specification

interface DeviceInfo {
    interface View {

        fun updateRecyclerView()
    }
    interface Presenter {
        fun onCreate(specifications: ArrayList<Specification>, device: Device)
    }
}