package com.sudzusama.comparephones.domain.repositories

import com.sudzusama.comparephones.domain.entities.Device

interface DeviceLocalRepo {
    fun addDevice(device : Device)
    fun removeDevice(device : Device)
}