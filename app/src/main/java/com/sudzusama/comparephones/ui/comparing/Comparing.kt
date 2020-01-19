package com.sudzusama.comparephones.ui.comparing

import android.content.Intent
import com.sudzusama.comparephones.data.model.DeviceInfo

interface Comparing {
    interface View {

        fun setupViewPager(
            firstDevice: DeviceInfo,
            secondDevice: DeviceInfo,
            firstDeviceName: String,
            secondDeviceName: String
        )
    }

    interface Presenter {

        fun onCreate(viewIntent: Intent)
    }
}