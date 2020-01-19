package com.sudzusama.comparephones.ui.main

import android.content.Intent
import com.sudzusama.comparephones.DEVICE_EXTRA
import com.sudzusama.comparephones.data.model.DeviceInfo
import javax.inject.Inject

class MainPresenter @Inject constructor(val view: Main.View) : Main.Presenter {

    private var firstDevice: DeviceInfo? = null
    private var secondDevice: DeviceInfo? = null

    override fun onViewResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val device = data?.getParcelableExtra<DeviceInfo>(DEVICE_EXTRA)
        device?.let {
            when (requestCode) {
                1 -> {
                    view.disableFirstDeviceButton()
                    view.enableFirstDeviceView()
                    view.loadFirstDeviceInfo(it.DeviceName)
                    firstDevice = it
                }
                2 -> {
                    view.disableSecondDeviceButton()
                    view.enableSecondDeviceView()
                    view.loadSecondDeviceInfo(it.DeviceName)
                    secondDevice = it
                }
            }

            if (firstDevice != null && secondDevice != null) {
                view.enableCompareButton()
            }
        }
    }

    override fun onCloseFirstDeviceView() {
        firstDevice = null
        view.disableFirstDeviceView()
        view.disableCompareButton()
        view.enableFirstDeviceButton()
    }

    override fun onCloseSecondDeviceView() {
        secondDevice = null
        view.disableSecondDeviceView()
        view.disableCompareButton()
        view.enableSecondDeviceButton()
    }

    override fun onChooseFirstDeviceButtonClicked(){
        view.startAddDeviceActivity(1)
    }

    override fun onChooseSecondDeviceButtonClicked(){
        view.startAddDeviceActivity(2)
    }

    override fun onCompareButtonPressed(){
        val fDevice = firstDevice
        val sDevice = secondDevice
        if (fDevice != null && sDevice != null) {
            view.startComparingActivity(fDevice, sDevice)
        }
        else {
            //TODO onCompareButtonPressed
        }
    }
}
