package com.sudzusama.comparephones.ui.comparing

import android.util.Log
import com.sudzusama.comparephones.domain.entity.Comparsion
import com.sudzusama.comparephones.domain.entity.Specification
import com.sudzusama.comparephones.domain.usecase.GetComparsionByIdUseCase
import com.sudzusama.comparephones.domain.usecase.GetRecentComparsionsUseCase
import com.sudzusama.comparephones.utils.SpecificationFormatterUtils
import javax.inject.Inject

class ComparingPresenter @Inject constructor(
    private val getRecentComparsionsUseCase: GetRecentComparsionsUseCase,
    private val getComparsionByIdUseCase: GetComparsionByIdUseCase
) : ComparingContract.Presenter {

    override var view: ComparingContract.View? = null

    private lateinit var specifications: ArrayList<Specification>

    override fun onCreate(specifications: ArrayList<Specification>) {
        this.specifications = specifications
        getLatestComparsion()
    }

    override fun onCreate(specs: ArrayList<Specification>, id: Int) {
        specifications = specs
        getComparsionById(id)
    }


    override fun onDetach() {
        getRecentComparsionsUseCase.dispose()
        getComparsionByIdUseCase.dispose()
        super.onDetach()
    }

    private fun getComparsionById(id: Int) {
        getComparsionByIdUseCase.setComparsionId(id)
        getComparsionByIdUseCase.subscribe(
            this::onComparsionReceived,
            this::onComparsionReceivingError
        )

    }

    private fun getLatestComparsion() {
        getRecentComparsionsUseCase.setComparsionAmount(1)
        getRecentComparsionsUseCase.subscribe(
            this::onLatestComparsionReceived,
            this::onComparsionReceivingError
        )
    }

    private fun onComparsionReceived(comparsion: Comparsion) {
        fillSpecificationsList(comparsion)
    }

    private fun onLatestComparsionReceived(list: List<Comparsion>) {
        val latestComparsion = list.firstOrNull()

        latestComparsion?.let {
            fillSpecificationsList(it)
        }
    }

    private fun fillSpecificationsList(comp: Comparsion) {
        view?.setFirstDeviceTitle(comp.firstDevice.DeviceName)
        view?.setSecondDeviceTitle(comp.secondDevice.DeviceName)

        val specs =
            SpecificationFormatterUtils.formatSpecifications(comp.firstDevice, comp.secondDevice)

        specifications.addAll(specs)
        view?.updateRecyclerView()
    }

    private fun onComparsionReceivingError(t: Throwable) {
        //TODO toast
        Log.e(this.javaClass.simpleName, t.message)
    }


}