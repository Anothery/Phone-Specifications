package com.sudzusama.comparephones.ui.comparing

import com.sudzusama.comparephones.domain.entity.Device
import com.sudzusama.comparephones.domain.entity.Specification

interface ComparingContract {
    interface View {
        fun setFirstDeviceTitle(title: String)
        fun setSecondDeviceTitle(title: String)
        fun updateRecyclerView()
    }

    interface Presenter {

        fun onCreate(specifications: ArrayList<Specification>)
    }
}