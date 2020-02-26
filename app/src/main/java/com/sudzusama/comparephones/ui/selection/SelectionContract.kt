package com.sudzusama.comparephones.ui.selection

import android.content.Intent
import com.sudzusama.comparephones.ui.base.MVPPresenter

interface SelectionContract {
    interface View {
        fun disableFirstDeviceButton()
        fun enableFirstDeviceButton()
        fun disableSecondDeviceButton()
        fun enableSecondDeviceButton()
        fun loadFirstDeviceInfo(deviceName: String)
        fun loadSecondDeviceInfo(deviceName: String)
        fun enableSecondDeviceView()
        fun disableSecondDeviceView()
        fun enableFirstDeviceView()
        fun disableFirstDeviceView()
        fun enableCompareButton()
        fun disableCompareButton()
        fun startComparingActivity()
        fun startAddDeviceActivity(requestCode: Int)
        fun enableButtons()
        fun disableButtons()
    }

    interface Presenter : MVPPresenter<View> {
        fun onViewResult(requestCode: Int, resultCode: Int, data: Intent?)
        fun onCloseFirstDeviceView()
        fun onCloseSecondDeviceView()
        fun onCompareButtonPressed()
        fun onChooseFirstDeviceButtonClicked()
        fun onChooseSecondDeviceButtonClicked()
        fun onViewRetained()
        fun onViewResumed()
    }

}