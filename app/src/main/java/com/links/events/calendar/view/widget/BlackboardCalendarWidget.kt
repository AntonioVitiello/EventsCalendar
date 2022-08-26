package com.links.events.calendar.view.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.core.view.isVisible
import com.links.events.calendar.R
import com.links.events.calendar.model.DeadlineModel
import com.links.events.calendar.tools.DateUtils
import com.links.events.calendar.view.widget.adapter.BlackboardCalendarAdapter
import kotlinx.android.synthetic.main.widget_blackboard_calendar.view.*

/**
 * Created by Antonio Vitiello
 */
class BlackboardCalendarWidget : FrameLayout {
    private lateinit var adapter: BlackboardCalendarAdapter

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        View.inflate(context, R.layout.widget_blackboard_calendar, this)
        initView()
    }

    private fun initView() {
        adapter = BlackboardCalendarAdapter(context)
        eventsRecycler.adapter = adapter
        dateText.text = DateUtils.formatDayMonth()
    }

    fun setDeadlines(deadlines: DeadlineModel) {
        val hasEvents = deadlines.descriptions.isNotEmpty()
        fillDate(deadlines.date)
        fillTitle(deadlines.date, hasEvents)
        if (hasEvents) {
            adapter.switchData(deadlines.descriptions)
            eventsRecycler.isVisible = true
        } else {
            eventsRecycler.isVisible = false
        }
    }

    private fun fillTitle(date: String, hasEvents: Boolean) {
        val today = date == DateUtils.formatDayOfYear()
        if (hasEvents) {
            titleText.text = context.getText(
                if (today) {
                    R.string.deadlines_for_today
                } else {
                    R.string.deadlines_for_selected_day
                }
            )
        } else {
            titleText.text = context.getText(
                if (today) {
                    R.string.no_deadlines_for_today
                } else {
                    R.string.no_deadlines_for_selected_day
                }
            )
        }
    }

    /**
     * Params: date - format yyyy/MM/dd  eg: "2022/06/19"
     */
    private fun fillDate(date: String) {
        val dayOfYear = DateUtils.parseDayOfYearOrNull(date)
        dateText.text = if (dayOfYear != null) {
            DateUtils.formatDayMonth(dayOfYear)
        } else {
            null
        }
    }

    fun clean(date: String) {
        dateText.text = DateUtils.parseDayOfYearOrNull(date)?.let {
            DateUtils.formatMonthYear(it, false)
        }
        titleText.text = context.getText(R.string.no_selected_day)
        eventsRecycler.isVisible = false
    }

}