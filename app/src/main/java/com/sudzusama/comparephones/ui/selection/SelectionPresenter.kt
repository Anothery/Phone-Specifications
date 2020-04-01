package com.sudzusama.comparephones.ui.selection

import android.content.Intent
import android.util.Log
import com.sudzusama.comparephones.domain.entity.Comparsion
import com.sudzusama.comparephones.domain.usecase.SaveComparsionUseCase
import com.sudzusama.comparephones.utils.DEVICE_NAME_EXTRA
import io.reactivex.subscribers.DisposableSubscriber
import javax.inject.Inject

class SelectionPresenter @Inject constructor(
    private val saveComparsionUseCase: SaveComparsionUseCase
) : SelectionContract.Presenter {
    override var view: SelectionContract.View? = null

    private var firstDeviceName: String? = null
    private var secondDeviceName: String? = null


    override fun onViewResult(requestCode: Int, resultCode: Int, data: Intent?) {
        data?.let { intent ->
            val deviceName = intent.getStringExtra(DEVICE_NAME_EXTRA)

            deviceName?.let {
                when (requestCode) {
                    1 -> {
                        firstDeviceName = it
                        loadFirstDevice(it)
                    }
                    2 -> {
                        secondDeviceName = it
                        loadSecondDevice(it)
                    }
                }
            }
        }

        if (firstDeviceName != null && secondDeviceName != null) {
            view?.enableCompareButton()
        }
    }


    override fun onViewRetained() {
        firstDeviceName?.let { loadFirstDevice(it) }
        secondDeviceName?.let { loadSecondDevice(it) }
    }

    override fun onDetach() {
        saveComparsionUseCase.dispose()
        super.onDetach()
    }

    private fun loadFirstDevice(fDevName: String) {
        view?.disableFirstDeviceButton()
        view?.enableFirstDeviceView()
        view?.loadFirstDeviceInfo(fDevName)
    }


    private fun loadSecondDevice(sDevName: String) {
        view?.disableSecondDeviceButton()
        view?.enableSecondDeviceView()
        view?.loadSecondDeviceInfo(sDevName)
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

    override fun onViewResumed() {
        view?.enableButtons()
    }

    override fun onCompareButtonPressed() {
        val fDevice = firstDeviceName
        val sDevice = secondDeviceName

        if (fDevice != null && sDevice != null) {
            view?.disableButtons()
            saveComparsionUseCase.setComparsion(fDevice, sDevice)
            saveComparsionUseCase.subscribe(object : DisposableSubscriber<Comparsion>() {
                override fun onComplete() {
                    onComparsionSaveSuccess()
                }

                override fun onNext(t: Comparsion?) {}

                override fun onError(t: Throwable) {
                    onComparsionSaveError(t)
                }
            })
        }
    }

    private fun onComparsionSaveSuccess() {
        view?.startComparingActivity()
    }

    private fun onComparsionSaveError(t: Throwable) {
        Log.e(this::class.java.simpleName, t.message)
    }


}
