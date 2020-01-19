package com.sudzusama.comparephones.ui.device

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sudzusama.comparephones.R
import com.sudzusama.comparephones.data.model.DeviceInfo

class DeviceInfoListAdapter(private val devices : ArrayList<DeviceInfo>) : RecyclerView.Adapter<DeviceInfoListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.)
    }
    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        init {

        }
    }
}