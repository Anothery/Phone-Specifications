package com.sudzusama.comparephones.data.db.repository

import com.sudzusama.comparephones.data.db.DevicesDatabase
import com.sudzusama.comparephones.domain.entities.Device
import com.sudzusama.comparephones.domain.repositories.DeviceLocalRepo
import com.sudzusama.comparephones.domain.repositories.DeviceRepo
import io.reactivex.Single
import javax.inject.Inject

class DeviceLocalRepository @Inject constructor(private val db : DevicesDatabase) : DeviceRepo, DeviceLocalRepo {
    override fun getDevices(deviceName: String): Single<List<Device>> {
        return db.devicesDao().getDevicesByTitle(deviceName)
    }

    override fun addDevice(device : Device){
        db.devicesDao().insertDevice(device)
    }

    override fun removeDevice(device : Device){
        db.devicesDao().deleteDevice(device)
    }
}