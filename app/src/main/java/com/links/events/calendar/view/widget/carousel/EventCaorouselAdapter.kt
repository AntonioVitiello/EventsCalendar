package com.links.events.calendar.view.widget.carousel

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.links.events.calendar.R
import com.links.events.calendar.model.DeadlineModel
import com.links.events.calendar.tools.DateUtils
import kotlinx.android.synthetic.main.item_event_carousel.view.*

class EventCaorouselAdapter(var listener: (DeadlineModel) -> Unit) : RecyclerView.Adapter<EventCaorouselAdapter.ViewHolder>() {
    private val deadlines = mutableListOf<DeadlineModel>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_event_carousel, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val deadline = deadlines[position]
        holder.bind(deadline)
    }

    override fun getItemCount() = deadlines.size

    @SuppressLint("NotifyDataSetChanged")
    fun switchData(data: List<DeadlineModel>?) {
        deadlines.clear()
        if (data != null) {
            deadlines.addAll(data)
        }
        notifyDataSetChanged()
    }

    fun deadlineOf(position: Int): DeadlineModel {
        return deadlines[position]
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(deadline: DeadlineModel) {
            val dayOfYearWithMonthName = DateUtils.parseDayOfYearOrNull(deadline.date)?.let { dayOfYear ->
                DateUtils.formatDayOfYearWithMonthName(dayOfYear)
            }
            itemView.dateText.text = dayOfYearWithMonthName
            itemView.descriptionText.text = deadline.reminder
            itemView.setOnClickListener { listener.invoke(deadline) }
        }
    }

}
