package com.links.events.calendar.view.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.links.events.calendar.R
import com.links.events.calendar.tools.DateUtils
import com.links.events.calendar.tools.capitalize
import com.links.events.calendar.view.widget.DayEventCalendarWidget.DateType.CURRENT_WITHOUT_EVENT
import com.links.events.calendar.view.widget.DayEventCalendarWidget.DateType.WITHOUT_EVENT
import kotlinx.android.synthetic.main.widget_event_calendar.view.*
import java.util.*

/**
 * Created by Antonio Vitiello
 */
class EventCalendarWidget : FrameLayout {
    private val todayCalendar = DateUtils.todayCalendar() //do not change date, clone instead!
    private var currentCalendar = todayCalendar.clone() as GregorianCalendar
    private lateinit var dayWidgets: List<DayEventCalendarWidget>


    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        View.inflate(context, R.layout.widget_event_calendar, this)
        initView()
    }

    private fun initView() {
        dayWidgets = listOf(
            day11Widget, day12Widget, day13Widget, day14Widget, day15Widget, day16Widget, day17Widget,
            day21Widget, day22Widget, day23Widget, day24Widget, day25Widget, day26Widget, day27Widget,
            day31Widget, day32Widget, day33Widget, day34Widget, day35Widget, day36Widget, day37Widget,
            day41Widget, day42Widget, day43Widget, day44Widget, day45Widget, day46Widget, day47Widget,
            day51Widget, day52Widget, day53Widget, day54Widget, day55Widget, day56Widget, day57Widget,
            day61Widget, day62Widget, day63Widget, day64Widget, day65Widget, day66Widget, day67Widget,
        )

        renderCalendar()

        leftArrowImage.setOnClickListener { prevMonth() }
        rightArrowImage.setOnClickListener { nextMonth() }
    }

    private fun renderCalendar() {
        // Month and Year
        monthText.text = DateUtils.formatMonthYear(currentCalendar.time).capitalize()

        val firstDayOfWeekInMonth = firstDayOfWeekInMonth(currentCalendar)
        val lastDayInMonth = currentCalendar.getActualMaximum(Calendar.DATE)

        // Render Last days of previous Month
        var day = lastDayOfPreviousMonth()
        var startIndex = firstDayOfWeekInMonth - 1
        var endIndex = 0
        for (i in startIndex downTo endIndex) {
            dayWidgets[i].setDate(day.toString())
            day--
        }

        // Render days of Current Month
        day = 1
        startIndex = firstDayOfWeekInMonth
        endIndex = lastDayInMonth + firstDayOfWeekInMonth
        for (i in startIndex until endIndex) {
            dayWidgets[i].setDate(day.toString(), WITHOUT_EVENT)
            day++
        }

        // Render first days of next Month
        day = 1
        startIndex = endIndex
        endIndex = dayWidgets.size
        for (i in startIndex until endIndex) {
            dayWidgets[i].setDate(day.toString())
            day++
        }
    }

    private fun lastDayOfPreviousMonth(): Int {
        currentCalendar.add(Calendar.MONTH, -1)
        return currentCalendar.getActualMaximum(Calendar.DATE).also {
            currentCalendar.add(Calendar.MONTH, 1)
        }
    }

    private fun firstDayOfWeekInMonth(calendar: GregorianCalendar): Int {
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        val dayOfWeek = DateUtils.formatDate(DateUtils.dayOfWeekFormat, calendar.time)
        return when {
            dayOfWeek.startsWith('l', true) -> 0
            dayOfWeek.startsWith("ma", true) -> 1
            dayOfWeek.startsWith("me", true) -> 2
            dayOfWeek.startsWith('g', true) -> 3
            dayOfWeek.startsWith('v', true) -> 4
            dayOfWeek.startsWith('s', true) -> 5
            else -> 6
        }
    }


    private fun prevMonth() {
        currentCalendar.add(Calendar.MONTH, -1)
        renderCalendar()
    }

    private fun nextMonth() {
        currentCalendar.add(Calendar.MONTH, 1)
        renderCalendar()
    }

}