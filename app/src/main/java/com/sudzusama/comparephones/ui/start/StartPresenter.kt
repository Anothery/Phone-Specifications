package com.sudzusama.comparephones.ui.start

class StartPresenter : StartContract.Presenter {
    private var view: StartContract.View? = null

    override fun onAttach(view: StartContract.View) {
        this.view = view
    }

    override fun onDetach() {
        view = null
    }
}