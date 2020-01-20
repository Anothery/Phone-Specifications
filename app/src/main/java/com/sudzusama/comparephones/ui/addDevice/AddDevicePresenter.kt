package com.sudzusama.comparephones.ui.addDevice

import android.util.Log
import com.sudzusama.comparephones.data.model.Device
import com.sudzusama.comparephones.data.repository.DeviceRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class AddDevicePresenter @Inject constructor(
    val view: AddDevice.View,
    private val deviceRepository: DeviceRepository
) : AddDevice.Presenter {

    private val disposable = CompositeDisposable()
    private lateinit var matches: ArrayList<Device>

    override fun onCreate(matches: ArrayList<Device>) {
        this.matches = matches
    }

    override fun observeFromText(observable: Observable<CharSequence>) {
        disposable.add(
            observable
                .debounce(200, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onTextArrived)
        )
    }

    private fun onTextArrived(char: CharSequence) {
        val text = char.toString()
        view.enableMatchesCount()
        if (text.isNotEmpty()) {
            disposable.add(
                deviceRepository.getDevices(text)
                    .subscribe(this::onDeviceListArrived, this::onDeviceListError)
            )
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

    private fun onDeviceListError(ex: Throwable) {
        Log.e(AddDevicePresenter::class.java.name, ex.message)
        matches.clear()
        view.setMatchesCount(0)
    }


    private fun updateMatchesList() {
        view.setMatchesCount(matches.size)
        view.updateRecyclerView()
    }

    override fun onDeviceItemClicked(device: Device) {
        view.disableMatchesCount()
        matches.clear()
        view.finishActivity(device)
    }

    override fun onDestroy() {
        disposable.clear()
    }

}