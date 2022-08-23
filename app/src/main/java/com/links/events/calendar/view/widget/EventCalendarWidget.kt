package com.links.events.calendar.view.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.links.events.calendar.R
import com.links.events.calendar.tools.DateUtils
import com.links.events.calendar.tools.capitalize
import kotlinx.android.synthetic.main.widget_event_calendar.view.*
import java.util.*

/**
 * Created by Antonio Vitiello
 */
class EventCalendarWidget : FrameLayout {
    private val todayCalendar = GregorianCalendar() //do not change date, clone instead!
    private var currentCalendar = todayCalendar.clone() as GregorianCalendar

// TODO:AV 23/08/2022 CLEANME
//    private val nowDay = todayCalendar.get(Calendar.DAY_OF_MONTH)
//    private val nowMonth = todayCalendar.get(Calendar.MONTH)
//    private val nowYear = todayCalendar.get(Calendar.YEAR)

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        View.inflate(context, R.layout.widget_event_calendar, this)
        initView()
    }

    private fun initView() {
        monthText.text = DateUtils.formatMonthYear().capitalize()

        leftArrowImage.setOnClickListener { prevMonth() }
        rightArrowImage.setOnClickListener { nextMonth() }
    }

    private fun prevMonth() {
        currentCalendar.add(Calendar.MONTH, -1)
        monthText.text = DateUtils.formatMonthYear(currentCalendar.time).capitalize()
    }

    private fun nextMonth() {
        currentCalendar.add(Calendar.MONTH, 1)
        monthText.text = DateUtils.formatMonthYear(currentCalendar.time).capitalize()
    }

}