package com.sudzusama.comparephones.ui.addDevice

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sudzusama.comparephones.R
import com.sudzusama.comparephones.data.model.Device


class AddDeviceMatchesAdapter(
    private val items: ArrayList<Device>,
    val listener: (Device) -> Unit
) :
    RecyclerView.Adapter<AddDeviceMatchesAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvDeviceName?.text = items[position].DeviceName
        holder.itemView.setOnClickListener { listener(items[position]) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.device_list_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvDeviceName: TextView? = null

        init {
            tvDeviceName = itemView.findViewById(R.id.devicelist_tv_devicename)
        }

    }
}












