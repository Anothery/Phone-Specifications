package com.sudzusama.comparephones.data.repository

import com.sudzusama.comparephones.domain.entities.Device
import com.sudzusama.comparephones.data.network.CPApiService
import com.sudzusama.comparephones.domain.repositories.DeviceRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DeviceRemoteRepository @Inject constructor(private val devicesApi: CPApiService) :
    DeviceRepository {

    override fun getDevices(deviceName: String): Single<List<Device>> {
        return devicesApi.getDevices(deviceName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}