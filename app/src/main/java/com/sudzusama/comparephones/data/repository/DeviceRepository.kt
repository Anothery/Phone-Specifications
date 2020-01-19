package com.sudzusama.comparephones.data.repository

import com.sudzusama.comparephones.data.model.Device
import io.reactivex.Single

interface DeviceRepository {
    fun getDevices(deviceName : String): Single<List<Device>>
}