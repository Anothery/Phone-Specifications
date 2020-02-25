package com.sudzusama.comparephones.ui.comparing

import android.util.Log
import com.sudzusama.comparephones.domain.entity.Comparsion
import com.sudzusama.comparephones.domain.entity.Specification
import com.sudzusama.comparephones.domain.usecase.UseCaseGetComparsionById
import com.sudzusama.comparephones.domain.usecase.UseCaseRecentComparsions
import com.sudzusama.comparephones.utils.SpecificationFormatterUtils
import javax.inject.Inject

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

    private fun fillSpecificationsList(comp: Comparsion) {
        view.setFirstDeviceTitle(comp.firstDevice.DeviceName)
        view.setSecondDeviceTitle(comp.secondDevice.DeviceName)

        val specs =
            SpecificationFormatterUtils.formatSpecifications(comp.firstDevice, comp.secondDevice)

        specifications.addAll(specs)
        view.updateRecyclerView()
    }

    private fun onComparsionReceivingError(t: Throwable) {
        //TODO toast
        Log.e(this.javaClass.simpleName, t.message)
    }


}