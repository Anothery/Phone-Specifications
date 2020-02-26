package com.sudzusama.comparephones.ui.base

interface MVPPresenter<V> {
    fun onAttach(view: V)
    fun onDetach()
}