package com.links.events.calendar.view.widget.blackboard

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.links.events.calendar.R
import kotlinx.android.synthetic.main.item_blackboard_calendar.view.*

class BlackboardCalendarAdapter : RecyclerView.Adapter<BlackboardCalendarAdapter.ViewHolder>() {
    private val eventTitles = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_blackboard_calendar, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val eventTitle = eventTitles[position]
        holder.bind(eventTitle, position)
    }

    override fun getItemCount() = eventTitles.size

    @SuppressLint("NotifyDataSetChanged")
    fun switchData(data: List<String>?) {
        eventTitles.clear()
        if (data != null) {
            eventTitles.addAll(data)
        }
        notifyDataSetChanged()
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(eventTitle: String, position: Int) {
            itemView.titleText.text = eventTitle
            if (eventTitles.size > 1) {
                val counter = "${position + 1}"
                itemView.counterText.text = counter
                itemView.counterText.isVisible = true
            } else {
                itemView.counterText.isVisible = false
            }
        }
    }

}
