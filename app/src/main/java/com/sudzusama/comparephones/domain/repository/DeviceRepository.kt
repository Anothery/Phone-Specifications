package com.sudzusama.comparephones.domain.repository

import com.sudzusama.comparephones.domain.entity.Comparsion
import com.sudzusama.comparephones.domain.entity.Device
import io.reactivex.Single

interface DeviceRepository {
    fun getDevices(deviceName: String): Single<List<Device>>
    fun getLatestComparsions(amount: Int): Single<List<Comparsion>>
    fun getLatestDevices(amount: Int): Single<List<Device>>
}