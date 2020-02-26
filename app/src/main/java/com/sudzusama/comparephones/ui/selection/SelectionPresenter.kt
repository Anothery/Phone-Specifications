package com.sudzusama.comparephones.ui.selection

import android.content.Intent
import com.sudzusama.comparephones.domain.entity.Comparsion
import com.sudzusama.comparephones.domain.usecase.UseCaseSaveComparsion
import com.sudzusama.comparephones.utils.DEVICE_NAME_EXTRA
import io.reactivex.subscribers.DisposableSubscriber
import javax.inject.Inject

class SelectionPresenter @Inject constructor(
    private val useCaseSaveComparsion: UseCaseSaveComparsion
) : SelectionContract.Presenter {
    private var view: SelectionContract.View? = null
    private var firstDeviceName: String? = null
    private var secondDeviceName: String? = null

    override fun onViewResult(requestCode: Int, resultCode: Int, data: Intent?) {
        data?.let { data ->
            val deviceName = data.getStringExtra(DEVICE_NAME_EXTRA)

            deviceName?.let {
                when (requestCode) {
                    1 -> {
                        firstDeviceName = it
                        loadFirstDevice()
                    }
                    2 -> {
                        secondDeviceName = it
                        loadSecondDevice()
                    }
                }

                if (firstDeviceName != null && secondDeviceName != null) {
                    view?.enableCompareButton()
                }
            }
        }
    }

    override fun updateViewAfterRetain() {
        loadFirstDevice()
        loadSecondDevice()
    }


    private fun loadFirstDevice() {
        view?.disableFirstDeviceButton()
        view?.enableFirstDeviceView()
        view?.loadFirstDeviceInfo(firstDeviceName!!)
    }


    private fun loadSecondDevice() {
        view?.disableSecondDeviceButton()
        view?.enableSecondDeviceView()
        view?.loadSecondDeviceInfo(secondDeviceName!!)
    }

    override fun onCloseFirstDeviceView() {
        firstDeviceName = null
        view?.disableFirstDeviceView()
        view?.disableCompareButton()
        view?.enableFirstDeviceButton()
    }

    override fun onCloseSecondDeviceView() {
        secondDeviceName = null
        view?.disableSecondDeviceView()
        view?.disableCompareButton()
        view?.enableSecondDeviceButton()
    }

    override fun onChooseFirstDeviceButtonClicked() {
        view?.disableButtons()
        view?.startAddDeviceActivity(1)
    }

    override fun onChooseSecondDeviceButtonClicked() {
        view?.disableButtons()
        view?.startAddDeviceActivity(2)
    }

    override fun onCompareButtonPressed() {
        val fDevice = firstDeviceName
        val sDevice = secondDeviceName
        if (fDevice != null && sDevice != null) {
            view?.disableButtons()
            useCaseSaveComparsion.setComparsion(fDevice, sDevice)
            useCaseSaveComparsion.subscribe(UseCaseSaveComparsionSubscriber())

        } else {
            //TODO onCompareButtonPressed error toast
        }
    }

    private fun onComparsionSaveSuccess() {
        view?.startComparingActivity()
    }

    private fun onComparsionSaveError() {
        //TODO
    }

    override fun onAttach(view: SelectionContract.View) {
        this.view = view
    }

    override fun onDetach() {
        this.view = null
    }

    override fun onResume() {
        view?.enableButtons()
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
