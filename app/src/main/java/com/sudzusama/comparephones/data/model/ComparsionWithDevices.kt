package com.sudzusama.comparephones.data.model

import androidx.room.Relation

data class ComparsionWithDevices(
    val comparsionId: Int,

    val firstDeviceName: String,

    val secondDeviceName: String,

    @Relation(parentColumn = "firstDeviceName", entityColumn = "DeviceName")
    val firstDevice: DeviceEntity,
    @Relation(parentColumn = "secondDeviceName", entityColumn = "DeviceName")
    val secondDevice: DeviceEntity
)