package com.sudzusama.comparephones.ui.comparing

import android.util.Log
import com.sudzusama.comparephones.domain.entity.Comparsion
import com.sudzusama.comparephones.domain.entity.Device
import com.sudzusama.comparephones.domain.entity.Specification
import com.sudzusama.comparephones.domain.usecase.UseCaseGetComparsionById
import com.sudzusama.comparephones.domain.usecase.UseCaseRecentComparsions
import javax.inject.Inject
import kotlin.reflect.full.memberProperties

class ComparingPresenter @Inject constructor(
    val view: ComparingContract.View,
    private val useCaseRecentComparsions: UseCaseRecentComparsions,
    private val useCaseGetComparsionById: UseCaseGetComparsionById
) : ComparingContract.Presenter {
    private lateinit var specifications: ArrayList<Specification>

    override fun onCreate(specs: ArrayList<Specification>) {
        specifications = specs
        getLatestComparsion()
    }

    override fun onCreate(specs: ArrayList<Specification>, id: Int) {
        specifications = specs
        getComparsionById(id)
    }

    private fun getComparsionById(id: Int) {
        useCaseGetComparsionById.setComparsionId(id)
        useCaseGetComparsionById.subscribe(
            this::onComparsionReceived,
            this::onComparsionReceivingError
        )

    }

    private fun getLatestComparsion() {
        useCaseRecentComparsions.setComparsionAmount(1)
        useCaseRecentComparsions.subscribe(
            this::onLatestComparsionReceived,
            this::onComparsionReceivingError
        )
    }

    private fun onComparsionReceived(comparsion: Comparsion) {
        fillSpecificationsList(comparsion)
    }

    private fun onLatestComparsionReceived(list: List<Comparsion>) {
        val latestComparsion = list[0]
        fillSpecificationsList(latestComparsion)
    }

    private fun fillSpecificationsList(comparsion: Comparsion) {
        view.setFirstDeviceTitle(comparsion.firstDevice.DeviceName)
        view.setSecondDeviceTitle(comparsion.secondDevice.DeviceName)
        for (prop in Device::class.memberProperties) {
            val name = prop.name
            val firstValue = prop.get(comparsion.firstDevice)
            val secondValue = prop.get(comparsion.secondDevice)
            if (firstValue != null && secondValue != null) {
                specifications.add(
                    Specification(name, firstValue.toString(), secondValue.toString())
                )
            }
        }
        view.updateRecyclerView()
    }

    private fun onComparsionReceivingError(t: Throwable) {
        //TODO toast
        Log.e(this.javaClass.simpleName, " TEST")
    }


}