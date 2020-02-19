package com.sudzusama.comparephones.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "comparsion")
data class Comparsion(

    @PrimaryKey(autoGenerate = true)
    val comparsionId: Int,

    @ForeignKey(
        parentColumns = ["DeviceName"],
        childColumns = ["firstDeviceName"],
        entity = Device::class
    )
    val firstDeviceName: String,

    @ForeignKey(
        parentColumns = ["DeviceName"],
        childColumns = ["secondDeviceName"],
        entity = Device::class
    )
    val secondDeviceName: String
)