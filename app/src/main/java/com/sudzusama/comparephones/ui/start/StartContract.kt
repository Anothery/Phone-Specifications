package com.sudzusama.comparephones.ui.start

interface StartContract {
    interface View {

    }

    interface Presenter {
        fun onDevicesItemSelected()
        fun onCompareItemSelected()
        fun onRecentItemSelected()
    }
}