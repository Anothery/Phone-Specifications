package com.sudzusama.comparephones.ui.addDevice

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sudzusama.comparephones.R
import com.sudzusama.comparephones.data.model.DeviceInfo


class AddDeviceMatchesAdapter(
    private val items: ArrayList<DeviceInfo>, private val context: Context,
    val listener: (DeviceInfo) -> Unit
) :
    RecyclerView.Adapter<AddDeviceMatchesAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvDeviceName?.text = items[position].DeviceName
        holder.itemView.setOnClickListener { listener(items[position]) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddDeviceMatchesAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.device_list_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvDeviceName: TextView? = null

        init {
            tvDeviceName = itemView.findViewById(R.id.tvDeviceName)
        }

    }
}












