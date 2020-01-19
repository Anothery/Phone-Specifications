package com.sudzusama.comparephones.ui.comparing

import android.content.Intent
import com.sudzusama.comparephones.FIRST_DEVICE_EXTRA
import com.sudzusama.comparephones.SECOND_DEVICE_EXTRA
import com.sudzusama.comparephones.data.model.Device
import javax.inject.Inject

class ComparingPresenter @Inject constructor(val view: Comparing.View) : Comparing.Presenter {


    override fun onCreate(viewIntent: Intent) {
        val firstDevice: Device = viewIntent.getParcelableExtra(FIRST_DEVICE_EXTRA)
        val secondDevice: Device = viewIntent.getParcelableExtra(SECOND_DEVICE_EXTRA)

        view.setupViewPager(
            firstDevice,
            secondDevice,
            firstDevice.DeviceName,
            secondDevice.DeviceName
        )
    }

}