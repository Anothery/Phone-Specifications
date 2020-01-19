package com.sudzusama.comparephones.ui.main

import android.content.Intent
import com.sudzusama.comparephones.data.model.Device

interface Main {
    interface View {
        fun disableFirstDeviceButton()
        fun enableFirstDeviceButton()
        fun disableSecondDeviceButton()
        fun enableSecondDeviceButton()
        fun loadFirstDeviceInfo(deviceName : String)
        fun loadSecondDeviceInfo(deviceName : String)
        fun enableSecondDeviceView()
        fun disableSecondDeviceView()
        fun enableFirstDeviceView()
        fun disableFirstDeviceView()
        fun enableCompareButton()
        fun disableCompareButton()
        fun startComparingActivity(firstDevice: Device, secondDevice: Device)
        fun startAddDeviceActivity(requestCode: Int)
        fun enableButtons()
        fun disableButtons()
    }

    interface Presenter {
        fun onViewResult(requestCode: Int, resultCode: Int, data: Intent?)
        fun onCloseFirstDeviceView()
        fun onCloseSecondDeviceView()
        fun onCompareButtonPressed()
        fun onChooseFirstDeviceButtonClicked()
        fun onChooseSecondDeviceButtonClicked()
    }
}