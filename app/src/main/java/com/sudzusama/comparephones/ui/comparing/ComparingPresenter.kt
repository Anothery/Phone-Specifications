package com.sudzusama.comparephones.ui.comparing

import android.content.Intent
import com.sudzusama.comparephones.utils.FIRST_DEVICE_EXTRA
import com.sudzusama.comparephones.utils.SECOND_DEVICE_EXTRA
import com.sudzusama.comparephones.domain.entities.Device
import javax.inject.Inject

class ComparingPresenter @Inject constructor(val view: ComparingContract.View) : ComparingContract.Presenter {


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