package com.sudzusama.comparephones.ui.deviceinfo

import com.sudzusama.comparephones.domain.entity.Device
import com.sudzusama.comparephones.domain.entity.Specification

interface DeviceInfoContract {
    interface View {

        fun updateRecyclerView()
    }
    interface Presenter {
        fun onCreate(specifications: ArrayList<Specification>, device: Device)
    }
}