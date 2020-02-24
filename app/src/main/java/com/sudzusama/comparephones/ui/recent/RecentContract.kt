package com.sudzusama.comparephones.ui.recent

import com.sudzusama.comparephones.domain.entity.Comparsion
import com.sudzusama.comparephones.domain.entity.Device

interface RecentContract {
    interface View{
        fun updateRecentComparsionsList()
        fun showRecentComparsionsList()
        fun hideRecentComparsionsList()
        fun startComparingActivity(id : Int)
    }
    interface Presenter{
        fun onRecentComparsionsItemClicked(comparsion: Comparsion)
        fun onViewCreated(comparsions: ArrayList<Comparsion>, devices: ArrayList<Device>)
        fun onViewResumed()
    }
}