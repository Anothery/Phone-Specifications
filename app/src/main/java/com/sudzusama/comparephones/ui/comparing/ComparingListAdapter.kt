package com.sudzusama.comparephones.ui.comparing

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sudzusama.comparephones.R
import com.sudzusama.comparephones.domain.entity.Specification

class ComparingListAdapter(
    private val specifications: ArrayList<Specification>,
    private val context: Context
) :
    RecyclerView.Adapter<ComparingListAdapter.ViewHolder>() {


    override fun getItemCount(): Int {
        return specifications.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvSpecTitle.text = context.getString(specifications[position].titleResId)
        holder.tvSpecFirstDescription.text = specifications[position].firstDeviceDescription
        holder.tvSpecSecondDescription.text = specifications[position].secondDeviceDescription
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.comparsion_specs_list_layout, parent, false)
        return ViewHolder(view)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvSpecTitle: TextView = itemView.findViewById(R.id.comparsion_specs_list_tv)
        val tvSpecFirstDescription: TextView = itemView.findViewById(R.id.comparsion_specs_tv_first)
        val tvSpecSecondDescription: TextView =
            itemView.findViewById(R.id.comparsion_specs_tv_second)

    }
}