package com.links.events.calendar.view.widget.blackboard

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.links.events.calendar.R
import kotlinx.android.synthetic.main.item_blackboard_calendar.view.*

class BlackboardCalendarAdapter : RecyclerView.Adapter<BlackboardCalendarAdapter.ViewHolder>() {
    private val descriptions = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_blackboard_calendar, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val description = descriptions[position]
        holder.bind(description, position)
    }

    override fun getItemCount() = descriptions.size

    @SuppressLint("NotifyDataSetChanged")
    fun switchData(data: List<String>?) {
        descriptions.clear()
        if (data != null) {
            descriptions.addAll(data)
        }
        notifyDataSetChanged()
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(description: String, position: Int) {
            itemView.descriptionText.text = description
            val counter = "${position + 1}"
            itemView.counterText.text = counter
        }
    }

}
