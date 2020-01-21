package com.sudzusama.comparephones.domain.repositories

import com.sudzusama.comparephones.domain.entities.Device
import io.reactivex.Single

interface DeviceRepository {
    fun getDevices(deviceName : String): Single<List<Device>>
}