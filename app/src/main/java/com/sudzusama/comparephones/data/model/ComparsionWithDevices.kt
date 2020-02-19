package com.sudzusama.comparephones.data.model

import androidx.room.Relation

data class ComparsionWithDevices(
    val firstDeviceName : String,
    val secondDeviceName : String,
    @Relation(parentColumn = "firstDeviceName", entityColumn = "DeviceName")
    val firstDevice :Device,
    @Relation(parentColumn = "secondDeviceName", entityColumn = "DeviceName")
    val secondDevice: Device
)