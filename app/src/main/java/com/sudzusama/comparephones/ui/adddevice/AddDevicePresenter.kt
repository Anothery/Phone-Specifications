package com.sudzusama.comparephones.ui.adddevice

import android.util.Log
import com.sudzusama.comparephones.domain.entity.Device
import com.sudzusama.comparephones.domain.usecase.GetDevicesByNameUseCase
import com.sudzusama.comparephones.domain.usecase.UpdateDeviceUseCase
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subscribers.DisposableSubscriber
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class AddDevicePresenter @Inject constructor(
    private val getDevicesByNameUseCase: GetDevicesByNameUseCase,
    private val updateDeviceUseCase: UpdateDeviceUseCase
) : AddDeviceContract.Presenter {

    override var view: AddDeviceContract.View? = null

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
        view?.enableMatchesCount()
        if (text.isNotEmpty()) {
            getDevicesByNameUseCase.searchDeviceByName(text)
            getDevicesByNameUseCase.subscribe(this::onDeviceListArrived, this::onDeviceListError)
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
        view?.setMatchesCount(matches.size)
        view?.updateRecyclerView()
    }

    override fun onDeviceItemClicked(device: Device) {
        updateDeviceLastUsed(device)
        view?.disableMatchesCount()
        matches.clear()
        view?.finishActivity(device.DeviceName)
    }

    private fun updateDeviceLastUsed(device: Device) {
        device.lastUsed = System.currentTimeMillis()
        updateDeviceUseCase.setDevice(device)
        updateDeviceUseCase.subscribe(object : DisposableSubscriber<Device>() {
            override fun onComplete() {}
            override fun onError(t: Throwable?) {
                //TODO Log error
            }

            override fun onNext(t: Device?) {}
        })

    }

    override fun onDetach() {
        getDevicesByNameUseCase.dispose()
        super.onDetach()
    }

}