package com.sudzusama.comparephones.domain.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "device")
@Parcelize
data class Device(
    val Brand: String?,
    @PrimaryKey
    val DeviceName: String,
    val _2g_bands: String?,
    val _3_5mm_jack_: String?,
    val _3g_bands: String?,
    val _4g_bands: String?,
    val alert_types: String?,
    val announced: String?,
    val battery_c: String?,
    val bluetooth: String?,
    val browser: String?,
    val card_slot: String?,
    val chipset: String?,
    val colors: String?,
    val cpu: String?,
    val dimensions: String?,
    val display_c: String?,
    val edge: String?,
    val features: String?,
    val features_c: String?,
    val gprs: String?,
    val gps: String?,
    val gpu: String?,
    val `internal`: String?,
    val java: String?,
    val loudspeaker_: String?,
    val messaging: String?,
    val multitouch: String?,
    val music_play: String?,
    val network_c: String?,
    val os: String?,
    val primary_: String?,
    val radio: String?,
    val resolution: String?,
    val secondary: String?,
    val sensors: String?,
    val sim: String?,
    val size: String?,
    val speed: String?,
    val stand_by: String?,
    val status: String?,
    val talk_time: String?,
    val technology: String?,
    val type: String?,
    val usb: String?,
    val video: String?,
    val weight: String?,
    val wlan: String?
) : Parcelable