package com.sudzusama.comparephones.ui.comparing

import android.content.Intent
import com.sudzusama.comparephones.domain.entities.Device

interface Comparing {
    interface View {

        fun setupViewPager(
            firstDevice: Device,
            secondDevice: Device,
            firstDeviceName: String,
            secondDeviceName: String
        )
    }

    interface Presenter {

        fun onCreate(viewIntent: Intent)
    }
}