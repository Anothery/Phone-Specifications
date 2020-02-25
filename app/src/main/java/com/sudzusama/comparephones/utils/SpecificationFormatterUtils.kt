package com.sudzusama.comparephones.utils

import com.sudzusama.comparephones.R
import com.sudzusama.comparephones.domain.entity.Device
import com.sudzusama.comparephones.domain.entity.Specification

class SpecificationFormatterUtils {
    companion object {
        fun formatSpecifications(fDev: Device, sDev: Device): ArrayList<Specification> {
            val list = ArrayList<Specification>()

            if (fDev.Brand != null && sDev.Brand != null) {
                list.add(Specification(R.string.device_brand, fDev.Brand, sDev.Brand))
            }
            if (fDev.announced != null && sDev.announced != null) {
                list.add(Specification(R.string.device_announced, fDev.announced, sDev.announced))
            }
            if (fDev.status != null && sDev.status != null) {
                list.add(Specification(R.string.device_status, fDev.status, sDev.status))
            }
            if (fDev.os != null && sDev.os != null) {
                list.add(Specification(R.string.device_os, fDev.os, sDev.os))
            }
            if (fDev.dimensions != null && sDev.dimensions != null) {
                list.add(Specification(R.string.device_dimensions, fDev.dimensions, sDev.dimensions))
            }
            if (fDev.features != null && sDev.features != null) {
                list.add(Specification(R.string.device_features, fDev.features, sDev.features))
            }
            if (fDev.features_c != null && sDev.features_c != null) {
                list.add(Specification(R.string.device_features_c, fDev.features_c, sDev.features_c))
            }
            if (fDev._3_5mm_jack_ != null && sDev._3_5mm_jack_ != null) {
                list.add(Specification(R.string.device__3_5mm_jack_, fDev._3_5mm_jack_, sDev._3_5mm_jack_))
            }
            if (fDev.battery_c != null && sDev.battery_c != null) {
                list.add(Specification(R.string.device_battery_c, fDev.battery_c, sDev.battery_c))
            }
            if (fDev.stand_by != null && sDev.stand_by != null) {
                list.add(Specification(R.string.device_stand_by, fDev.stand_by, sDev.stand_by))
            }
            if (fDev.talk_time != null && sDev.talk_time != null) {
                list.add(Specification(R.string.device_talk_time, fDev.talk_time, sDev.talk_time))
            }
            if (fDev.weight != null && sDev.weight != null) {
                list.add(Specification(R.string.device_weight, fDev.weight, sDev.weight))
            }
            if (fDev.sim != null && sDev.sim != null) {
                list.add(Specification(R.string.device_sim, fDev.sim, sDev.sim))
            }
            if (fDev.card_slot != null && sDev.card_slot != null) {
                list.add(Specification(R.string.device_card_slot, fDev.card_slot, sDev.card_slot))
            }
            if (fDev._2g_bands != null && sDev._2g_bands != null) {
                list.add(Specification(R.string.device__2g_bands, fDev._2g_bands, sDev._2g_bands))
            }
            if (fDev._3g_bands != null && sDev._3g_bands != null) {
                list.add(Specification(R.string.device__3g_bands, fDev._3g_bands, sDev._3g_bands))
            }
            if (fDev._4g_bands != null && sDev._4g_bands != null) {
                list.add(Specification(R.string.device__4g_bands, fDev._4g_bands, sDev._4g_bands))
            }
            if (fDev.primary_ != null && sDev.primary_ != null) {
                list.add(Specification(R.string.device_primary_, fDev.primary_, sDev.primary_))
            }
            if (fDev.network_c != null && sDev.network_c != null) {
                list.add(Specification(R.string.device_network_c, fDev.network_c, sDev.network_c))
            }
            if (fDev.resolution != null && sDev.resolution != null) {
                list.add(Specification(R.string.device_resolution, fDev.resolution, sDev.resolution))
            }
            if (fDev.colors != null && sDev.colors != null) {
                list.add(Specification(R.string.device_colors, fDev.colors, sDev.colors))
            }
            if (fDev.usb != null && sDev.usb != null) {
                list.add(Specification(R.string.device_usb, fDev.usb, sDev.usb))
            }
            if (fDev.wlan != null && sDev.wlan != null) {
                list.add(Specification(R.string.device_wlan, fDev.wlan, sDev.wlan))
            }
            if (fDev.video != null && sDev.video != null) {
                list.add(Specification(R.string.device_video, fDev.video, sDev.video))
            }
            if (fDev.display_c != null && sDev.display_c != null) {
                list.add(Specification(R.string.device_display_c, fDev.display_c, sDev.display_c))
            }
            if (fDev.chipset != null && sDev.chipset != null) {
                list.add(Specification(R.string.device_chipset, fDev.chipset, sDev.chipset))
            }
            if (fDev.technology != null && sDev.technology != null) {
                list.add(Specification(R.string.device_technology, fDev.technology, sDev.technology))
            }
            if (fDev.type != null && sDev.type != null) {
                list.add(Specification(R.string.device_type, fDev.type, sDev.type))
            }
            if (fDev.speed != null && sDev.speed != null) {
                list.add(Specification(R.string.device_speed, fDev.speed, sDev.speed))
            }
            if (fDev.sensors != null && sDev.sensors != null) {
                list.add(Specification(R.string.device_sensors, fDev.sensors, sDev.sensors))
            }
            if (fDev.gpu != null && sDev.gpu != null) {
                list.add(Specification(R.string.device_gpu, fDev.gpu, sDev.gpu))
            }
            if (fDev.multitouch != null && sDev.multitouch != null) {
                list.add(Specification(R.string.device_multitouch, fDev.multitouch, sDev.multitouch))
            }
            if (fDev.cpu != null && sDev.cpu != null) {
                list.add(Specification(R.string.device_cpu, fDev.cpu, sDev.cpu))
            }
            if (fDev.gps != null && sDev.gps != null) {
                list.add(Specification(R.string.device_gps, fDev.gps, sDev.gps))
            }
            if (fDev.bluetooth != null && sDev.bluetooth != null) {
                list.add(Specification(R.string.device_bluetooth, fDev.bluetooth, sDev.bluetooth))
            }
            if (fDev.browser != null && sDev.browser != null) {
                list.add(Specification(R.string.device_browser, fDev.browser, sDev.browser))
            }
            if (fDev.internal != null && sDev.internal != null) {
                list.add(Specification(R.string.device_internal, fDev.internal, sDev.internal))
            }
            if (fDev.secondary != null && sDev.secondary != null) {
                list.add(Specification(R.string.device_secondary, fDev.secondary, sDev.secondary))
            }
            if (fDev.radio != null && sDev.radio != null) {
                list.add(Specification(R.string.device_radio, fDev.radio, sDev.radio))
            }
            if (fDev.alert_types != null && sDev.alert_types != null) {
                list.add(Specification(R.string.device_alert_types, fDev.alert_types, sDev.alert_types))
            }
            if (fDev.edge != null && sDev.edge != null) {
                list.add(Specification(R.string.device_edge, fDev.edge, sDev.edge))
            }
            if (fDev.music_play != null && sDev.music_play != null) {
                list.add(Specification(R.string.device_music_play, fDev.music_play, sDev.music_play))
            }

            return list

        }
    }


}