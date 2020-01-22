package com.sudzusama.comparephones.ui.main

import android.content.Intent
import com.sudzusama.comparephones.utils.DEVICE_EXTRA
import com.sudzusama.comparephones.domain.entities.Device
import com.sudzusama.comparephones.domain.repositories.DeviceLocalRepo
import javax.inject.Inject

class MainPresenter @Inject constructor(val view: Main.View, private val db : DeviceLocalRepo) : Main.Presenter {

    private var firstDevice: Device? = null
    private var secondDevice: Device? = null

    override fun onViewResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val device = data?.getParcelableExtra<Device>(DEVICE_EXTRA)
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

    override fun onChooseFirstDeviceButtonClicked() {
        view.disableButtons()
        view.startAddDeviceActivity(1)
    }

    override fun onChooseSecondDeviceButtonClicked() {
        view.disableButtons()
        view.startAddDeviceActivity(2)
    }

    override fun onCompareButtonPressed() {
        val fDevice = firstDevice
        val sDevice = secondDevice
        if (fDevice != null && sDevice != null) {
            view.disableButtons()
            view.startComparingActivity(fDevice, sDevice)
        } else {
            //TODO onCompareButtonPressed error toast
        }
    }

    override fun onResume() {
        view.enableButtons()
    }

}
