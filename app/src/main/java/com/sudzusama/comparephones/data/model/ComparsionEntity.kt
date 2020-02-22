package com.sudzusama.comparephones.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "comparsion")
data class ComparsionEntity(

    @PrimaryKey(autoGenerate = true)
    val comparsionId: Int,

    @ForeignKey(
        parentColumns = ["DeviceName"],
        childColumns = ["firstDeviceName"],
        entity = DeviceEntity::class
    )
    val firstDeviceName: String,

    @ForeignKey(
        parentColumns = ["DeviceName"],
        childColumns = ["secondDeviceName"],
        entity = DeviceEntity::class
    )
    val secondDeviceName: String
)