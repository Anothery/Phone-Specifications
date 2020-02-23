package com.sudzusama.comparephones.ui.selection

import android.content.Intent
import com.sudzusama.comparephones.domain.entity.Comparsion
import com.sudzusama.comparephones.domain.entity.Device
import com.sudzusama.comparephones.domain.usecase.UseCaseSaveComparsion
import com.sudzusama.comparephones.utils.DEVICE_EXTRA
import io.reactivex.subscribers.DisposableSubscriber
import javax.inject.Inject

class SelectionPresenter @Inject constructor(
    val view: SelectionContract.View,
    private val useCaseSaveComparsion: UseCaseSaveComparsion
) :
    SelectionContract.Presenter {

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
            useCaseSaveComparsion.createComparsion(fDevice, sDevice)
            useCaseSaveComparsion.subscribe(UseCaseSaveComparsionSubscriber())

        } else {
            //TODO onCompareButtonPressed error toast
        }
    }

    private fun onComparsionSaveSuccess() {
        view.startComparingActivity()
    }

    private fun onComparsionSaveError() {
        //TODO
    }


    override fun onResume() {
        view.enableButtons()
    }

    internal inner class UseCaseSaveComparsionSubscriber : DisposableSubscriber<Comparsion>() {
        override fun onComplete() {
            onComparsionSaveSuccess()
        }

        override fun onNext(t: Comparsion?) {}

        override fun onError(t: Throwable?) {
            onComparsionSaveError()
        }

    }

}
