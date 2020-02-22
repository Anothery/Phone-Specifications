package com.sudzusama.comparephones.ui.deviceinfo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sudzusama.comparephones.R
import com.sudzusama.comparephones.domain.entity.Specification

class DeviceInfoListAdapter(private val specifications: ArrayList<Specification>) :
    RecyclerView.Adapter<DeviceInfoListAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return specifications.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvSpecTitle.text = specifications[position].title
        holder.tvSpecDescription.text = specifications[position].description
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.deviceinfo_list_layout, parent, false)
        return ViewHolder(view)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvSpecTitle: TextView
        var tvSpecDescription: TextView

        init {
            tvSpecTitle = itemView.findViewById(R.id.deviceinfolist_tv_spectitle)
            tvSpecDescription = itemView.findViewById(R.id.deviceinfolist_tv_specdescription)
        }
    }
}