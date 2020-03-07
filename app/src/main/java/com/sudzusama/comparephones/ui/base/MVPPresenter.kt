package com.sudzusama.comparephones.ui.base

interface MVPPresenter<V> {
    var view: V?

    fun onAttach(view: V) {
        this.view = view
    }

    fun onDetach() {
        view = null
    }
}