package com.sudzusama.comparephones.ui.recent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sudzusama.comparephones.R
import com.sudzusama.comparephones.domain.entity.Comparsion
import com.sudzusama.comparephones.utils.AnimationUtils

class RecentComparsionsAdapter(
    private val items: ArrayList<Comparsion>,
    val listener: (Comparsion) -> Unit
) :
    RecyclerView.Adapter<RecentComparsionsAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvFirstDevice.text = items[position].firstDevice.DeviceName
        holder.tvSecondDevice.text = items[position].secondDevice.DeviceName
        holder.itemView.setOnClickListener { listener(items[position]) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recent_comparsions_list_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvFirstDevice: TextView = itemView.findViewById(R.id.recent_comparsions_tv_first_device)
        val tvSecondDevice: TextView =
            itemView.findViewById(R.id.recent_comparsions_tv_second_device)
    }
}


