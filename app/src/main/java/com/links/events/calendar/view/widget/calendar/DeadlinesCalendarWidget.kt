package com.links.events.calendar.view.widget.calendar

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.links.events.calendar.R
import com.links.events.calendar.model.DeadlineModel
import com.links.events.calendar.tools.DateUtils.Companion.dayOfWeekFormat
import com.links.events.calendar.tools.DateUtils.Companion.formatDate
import com.links.events.calendar.tools.DateUtils.Companion.formatDayOfYear
import com.links.events.calendar.tools.DateUtils.Companion.formatMonthYear
import com.links.events.calendar.tools.DateUtils.Companion.parseDayOfYearOrNull
import com.links.events.calendar.tools.DateUtils.Companion.todayCalendar
import com.links.events.calendar.view.widget.calendar.DeadlineDayWidget.DateType.WITHOUT_EVENT
import kotlinx.android.synthetic.main.widget_event_calendar.view.*
import java.util.*

/**
 * Created by Antonio Vitiello
 */
class DeadlinesCalendarWidget : FrameLayout, IDayClick {
    private val todayCalendar = todayCalendar() //do not change date, clone instead!
    private var currentCalendar = todayCalendar.clone() as GregorianCalendar
    private lateinit var dayWidgets: List<DeadlineDayWidget>
    private var firstDayOfMonthOffset = 0
    private var dayWidgetSelected: DeadlineDayWidget? = null
    private var daySelectionListener: ((DeadlineModel) -> Unit)? = null
    private var monthChangeListener: ((String) -> Unit)? = null

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        View.inflate(context, R.layout.widget_event_calendar, this)
        initView()
    }

    // REM:AV 24/08/2022 MonthDisplayHelper
    // val helper = MonthDisplayHelper(2022, 4, Calendar.MONDAY)
    // println("year=${helper.year}")
    // println("month=${helper.month}")
    // println("numberOfDaysInMonth=${helper.numberOfDaysInMonth}")
    // println("offset=${helper.offset}")
    // println("weekStartDay=${helper.weekStartDay}")
    // println("getRowOf=${helper.getRowOf(10)}")
    // println("getColumnOf=${helper.getColumnOf(10)}")

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

    override fun onNewSelection(dayWidget: DeadlineDayWidget) {
        if (dayWidget.dayInMonth) {
            // execute selection and store new selection state
            dayWidget.daySelected = true
            if (dayWidget != dayWidgetSelected) {
                dayWidgetSelected?.daySelected = false
                dayWidgetSelected = dayWidget
            }
            // invoke listener for those interested
            daySelectionListener?.let { listener ->
                // there's an event in this day selected?
                dayWidget.eventData?.let { deadline ->
                    listener.invoke(deadline)
                } ?: run {
                    val dayOfMonth = dayWidget.getDayOfMonth()
                    val date = getDayOfYear(dayOfMonth)
                    val deadlineModel = DeadlineModel(dayOfMonth.toString(), date)
                    listener.invoke(deadlineModel)
                }
            }
        }
    }

    private fun getDayOfYear(dayOfMonth: Int): String {
        val calendar = currentCalendar.clone() as GregorianCalendar
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        return formatDayOfYear(calendar.time)
    }

    private fun renderCalendar() {
        // Month and Year
        monthText.text = formatMonthYear(currentCalendar.time)

        firstDayOfMonthOffset = firstDayOfMonthOffset(currentCalendar)
        val lastDayInMonth = currentCalendar.getActualMaximum(Calendar.DATE)

        // Render Last days of previous Month
        var day = lastDayOfPreviousMonth()
        var startIndex = firstDayOfMonthOffset - 1
        var endIndex = 0
        for (i in startIndex downTo endIndex) {
            dayWidgets[i].setDate(day.toString())
            day--
        }

        // Render days of Current Month
        day = 1
        startIndex = firstDayOfMonthOffset
        endIndex = lastDayInMonth + firstDayOfMonthOffset
        for (i in startIndex until endIndex) {
            dayWidgets[i].setDate(day.toString(), WITHOUT_EVENT, true)
            // for Sunday text color is gray
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

        // Select today if in this Month
        if (
            currentCalendar.get(Calendar.YEAR) == todayCalendar.get(Calendar.YEAR)
            && currentCalendar.get(Calendar.MONTH) == todayCalendar.get(Calendar.MONTH)
        ) {
            val today = todayCalendar.get(Calendar.DAY_OF_MONTH)
            dayWidgets[getIndexOf(today)].let { dayWidget ->
                dayWidget.today = true
                dayWidgetSelected = dayWidget
            }
        }

        // Sundays have gray color
        for (i in 6 until dayWidgets.size step 7) {
            dayWidgets[i].sunday = true
        }
    }

    private fun getIndexOf(day: Int): Int {
        return day + firstDayOfMonthOffset - 1
    }

    private fun getIndexOf2(day: Int): Int {
        val rowIndex = getRowOf(day)
        val columnIndex = getColumnOf(day)
        return rowIndex * 7 + columnIndex
    }

    private fun getRowOf(day: Int): Int {
        return (day + firstDayOfMonthOffset - 1) / 7
    }

    private fun getColumnOf(day: Int): Int {
        return (day + firstDayOfMonthOffset - 1) % 7
    }

    private fun lastDayOfPreviousMonth(): Int {
        currentCalendar.add(Calendar.MONTH, -1)
        return currentCalendar.getActualMaximum(Calendar.DATE).also {
            currentCalendar.add(Calendar.MONTH, 1)
        }
    }

    private fun firstDayOfMonthOffset(calendar: GregorianCalendar): Int {
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        val dayOfWeek = formatDate(dayOfWeekFormat, calendar.time)
        return when {
            dayOfWeek.startsWith('l', true) -> 0  // lun
            dayOfWeek.startsWith("ma", true) -> 1 // mar
            dayOfWeek.startsWith("me", true) -> 2 // mer
            dayOfWeek.startsWith('g', true) -> 3  // gio
            dayOfWeek.startsWith('v', true) -> 4  // ven
            dayOfWeek.startsWith('s', true) -> 5  // sab
            else -> 6                             // dom
        }
    }

    private fun prevMonth() {
        currentCalendar.add(Calendar.MONTH, -1)
        renderCalendar()
        monthChangeListener?.invoke(formatDayOfYear(currentCalendar.time))
    }

    private fun nextMonth() {
        currentCalendar.add(Calendar.MONTH, 1)
        renderCalendar()
        monthChangeListener?.invoke(formatDayOfYear(currentCalendar.time))
    }

    /**
     * Add a list of deadlines as String to the calendar
     *
     * Params: dates - a list of date as String with format: yyyy/MM/dd
     */
    fun switchDeadlines(deadlines: List<DeadlineModel>) {
        deadlines.forEach(::addDeadline)
    }

    /**
     * Add just one deadline as String to the calendar
     *
     * Params: date - a date string with format: yyyy/MM/dd
     */
    fun addDeadline(deadline: DeadlineModel) {
        parseDayOfYearOrNull(deadline.date)?.let { dayOfYear ->
            val eventCalendar = todayCalendar.clone() as GregorianCalendar
            eventCalendar.time = dayOfYear
            if (
                currentCalendar.get(Calendar.YEAR) == eventCalendar.get(Calendar.YEAR)
                && currentCalendar.get(Calendar.MONTH) == eventCalendar.get(Calendar.MONTH)
            ) {
                val eventDay = eventCalendar.get(Calendar.DAY_OF_MONTH)
                dayWidgets[getIndexOf(eventDay)].let { dayWidget ->
                    dayWidget.eventData = deadline
                    dayWidget.dayWithEvent = true
                    if (dayWidget.today) {
                        // invoke listener for those interested
                        daySelectionListener?.let { listener ->
                            dayWidget.eventData?.let { deadline ->
                                listener.invoke(deadline)
                            }
                        }
                    }
                }
            }
        }
    }

    fun setDaySelectionListener(listener: (DeadlineModel) -> Unit) {
        daySelectionListener = listener
    }

    fun setMonthChangeListener(listener: (String) -> Unit) {
        monthChangeListener = listener
    }

}