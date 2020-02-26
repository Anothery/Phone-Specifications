package com.sudzusama.comparephones.ui.adddevice

import android.util.Log
import com.sudzusama.comparephones.domain.entity.Device
import com.sudzusama.comparephones.domain.usecase.UseCaseDevices
import com.sudzusama.comparephones.domain.usecase.UseCaseSaveComparsion
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class AddDevicePresenter @Inject constructor(
    val view: AddDeviceContract.View,
    private val useCaseDevices: UseCaseDevices
) : AddDeviceContract.Presenter {

    private lateinit var matches: ArrayList<Device>
    private val disposable = CompositeDisposable()

    companion object {
        val TAG: String = this::class.java.simpleName
    }

    override fun onCreate(matches: ArrayList<Device>) {
        this.matches = matches
    }

    override fun observeFromText(observable: Observable<CharSequence>) {
        disposable.add(
            observable
                .debounce(200, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onTextArrived, this::onObserveFromTextError)
        )
    }

    private fun onObserveFromTextError(throwable: Throwable) {
        // TODO View toast
        Log.e(TAG, throwable.message)
    }

    private fun onTextArrived(char: CharSequence) {
        val text = char.toString()
        view.enableMatchesCount()
        if (text.isNotEmpty()) {
            useCaseDevices.searchDeviceByName(text)
            useCaseDevices.subscribe(this::onDeviceListArrived, this::onDeviceListError)
        } else {
            matches.clear()
        }
    }

    private fun onDeviceListArrived(deviceList: List<Device>) {
        if (deviceList.isNotEmpty()) {
            matches.clear()
            matches.addAll(deviceList)
            updateMatchesList()
        } else {
            matches.addAll(deviceList)
            updateMatchesList()
        }
    }

    private fun onDeviceListError(t: Throwable) {
        //TODO("View toast")
        Log.e(TAG, t.message)
        matches.clear()
        updateMatchesList()

    }

    private fun updateMatchesList() {
        view.setMatchesCount(matches.size)
        view.updateRecyclerView()
    }

    override fun onDeviceItemClicked(device: Device) {
        view.disableMatchesCount()
        matches.clear()
        view.finishActivity(device.DeviceName)
    }

    override fun onDestroy() {
        useCaseDevices.dispose()
    }

}