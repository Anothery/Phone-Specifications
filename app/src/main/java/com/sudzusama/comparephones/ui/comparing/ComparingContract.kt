package com.sudzusama.comparephones.ui.comparing

import com.sudzusama.comparephones.domain.entity.Specification
import com.sudzusama.comparephones.ui.base.MVPPresenter

interface ComparingContract {

    interface View {
        fun setFirstDeviceTitle(title: String)
        fun setSecondDeviceTitle(title: String)
        fun updateRecyclerView()
    }

    interface Presenter : MVPPresenter<View> {

        fun onCreate(specifications: ArrayList<Specification>)
        fun onCreate(specs: ArrayList<Specification>, id: Int)
    }
}