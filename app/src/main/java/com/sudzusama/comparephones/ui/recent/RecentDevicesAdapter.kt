package com.sudzusama.comparephones.ui.recent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sudzusama.comparephones.R
import com.sudzusama.comparephones.domain.entity.Device

class RecentDevicesAdapter(
    private val items: ArrayList<Device>,
    val listener: (Device) -> Unit
) : RecyclerView.Adapter<RecentDevicesAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvDevicename.text = items[position].DeviceName
        holder.itemView.setOnClickListener { listener(items[position]) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recent_devices_list_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvDevicename: TextView = itemView.findViewById(R.id.recent_devices_tv_device)
        val ivDevicePhoto: ImageView = itemView.findViewById(R.id.recent_devices_iv_photo)
    }
}


