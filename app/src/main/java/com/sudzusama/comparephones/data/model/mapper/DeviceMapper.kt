package com.sudzusama.comparephones.data.model.mapper

import com.sudzusama.comparephones.data.model.Device
import com.sudzusama.comparephones.domain.entities.Device as DeviceDomain

class DeviceMapper  : Mapper<Device, DeviceDomain> {
    override fun map(source: Device): DeviceDomain {
        return com.sudzusama.comparephones.domain.entities.Device(
            source.Brand,
            source.DeviceName,
            source._2g_bands,
            source._3_5mm_jack_,
            source._3g_bands,
            source._4g_bands,
            source.alert_types,
            source.announced,
            source.battery_c,
            source.bluetooth,
            source.browser,
            source.card_slot,
            source.chipset,
            source.colors,
            source.cpu,
            source.dimensions,
            source.display_c,
            source.edge,
            source.features,
            source.features_c,
            source.gprs,
            source.gps,
            source.gpu,
            source.internal,
            source.java,
            source.loudspeaker_,
            source.messaging,
            source.multitouch,
            source.music_play,
            source.network_c,
            source.os,
            source.primary_,
            source.radio,
            source.resolution,
            source.secondary,
            source.sensors,
            source.sim,
            source.size,
            source.speed,
            source.stand_by,
            source.status,
            source.talk_time,
            source.technology,
            source.type,
            source.usb,
            source.video,
            source.weight,
            source.wlan
        )
    }

}