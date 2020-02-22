package com.sudzusama.comparephones.domain.repositories

import com.sudzusama.comparephones.domain.entities.Comparsion
import com.sudzusama.comparephones.domain.entities.Device
import io.reactivex.Single

interface DeviceRepository {
    fun getDevices(deviceName: String): Single<List<Device>>
    fun getLatestComparsions(amount: Int): Single<List<Comparsion>>
    fun getLatestDevices(amount: Int): Single<List<com.sudzusama.comparephones.data.model.Device>>
}