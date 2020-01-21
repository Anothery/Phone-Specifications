package com.sudzusama.comparephones.ui.deviceinfo

import com.sudzusama.comparephones.domain.entities.Device
import com.sudzusama.comparephones.domain.entities.Specification
import javax.inject.Inject
import kotlin.reflect.full.memberProperties

class DeviceInfoPresenter @Inject constructor(
    val view: DeviceInfo.View
) : DeviceInfo.Presenter {

    private lateinit var specifications: ArrayList<Specification>

    override fun onCreate(specs: ArrayList<Specification>, device: Device) {
        specifications = specs
        convertDeviceIntoArray(device)
        view.updateRecyclerView()
    }

    private fun convertDeviceIntoArray(device: Device) {
        for (prop in Device::class.memberProperties) {
            val name = prop.name
            val value = prop.get(device)
            if (value != null) {
                specifications.add(
                    Specification(
                        name,
                        value.toString()
                    )
                )
            }
        }
    }
}