package com.links.events.calendar

import android.util.MonthDisplayHelper
import com.links.events.calendar.tools.DateUtils
import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.DayOfWeek
import java.util.*
import java.util.Calendar.LONG_FORMAT

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun todayCalendar() {
        val todayCalendar = DateUtils.todayCalendar() //do not change date, clone instead!
        val currentCalendar = todayCalendar.clone() as GregorianCalendar
        println("max_min_date: todayCalendar=${DateUtils.formatItDate(todayCalendar.time)}")
        println("max_min_date: currentCalendar=${DateUtils.formatItDate(currentCalendar.time)}")
    }

    @Test
    fun max_min_date() {
//        val tz = TimeZone.getDefault()
//        println("TimeZone   " + tz.getDisplayName(false, TimeZone.SHORT).toString() + " Timezone id :: " + tz.id)

//        val todayCalendar = GregorianCalendar(Locale.ITALY)
//        val todayCalendar = GregorianCalendar(Locale.ITALY).also { it.add(Calendar.MONTH, -3) }
//        val todayCalendar = GregorianCalendar(Locale.ITALY).also { it.set(Calendar.MONTH, 0) }

        val timezone = TimeZone.getDefault()
        val todayCalendar = GregorianCalendar(timezone).also { it.set(Calendar.MONTH, 0) }

        val actualMaximum = todayCalendar.getActualMaximum(Calendar.DATE)
        println("max_min_date: actualMaximum=$actualMaximum")

//        val day_of_week = todayCalendar.getActualMinimum(Calendar.DAY_OF_WEEK)
//        val day_of_month = todayCalendar.getActualMinimum(Calendar.DAY_OF_MONTH)
//        val day_of_week_in_month = todayCalendar.getActualMinimum(Calendar.DAY_OF_WEEK_IN_MONTH)
//        println("max_min_date: actualMaximum=$actualMaximum, day_of_week=$day_of_week, day_of_month=$day_of_month, day_of_week_in_month=$day_of_week_in_month")

//        val minimalDaysInFirstWeek = todayCalendar.getMinimalDaysInFirstWeek()
//        println("max_min_date: minimalDaysInFirstWeek=$minimalDaysInFirstWeek")

        todayCalendar.set(Calendar.DAY_OF_MONTH, 1)
        val itDate = DateUtils.formatItDate(todayCalendar.time)
        println("max_min_date: italian-date=$itDate")

        val day_of_week2 = todayCalendar.get(Calendar.DAY_OF_WEEK)
        val name = DayOfWeek.of(day_of_week2)
        println("max_min_date: day_of_week2=$day_of_week2, name=$name")

        val displayName = todayCalendar.getDisplayName(Calendar.DAY_OF_WEEK, LONG_FORMAT, Locale.ITALY)
        println("max_min_date: displayName=$displayName")

//        todayCalendar.firstDayOfWeek = Calendar.MONDAY
        println("max_min_date: firstDayOfWeek=${todayCalendar.firstDayOfWeek}, ${DayOfWeek.of(todayCalendar.firstDayOfWeek)}")

//        val day_of_week3 = todayCalendar.getMinimum(Calendar.DAY_OF_WEEK)
//        println("max_min_date: day_of_week3=$day_of_week3")

    }

    @Test
    fun min_date() {
        val todayCalendar = DateUtils.todayCalendar()
        val currentCalendar = todayCalendar.clone() as GregorianCalendar

        currentCalendar.add(Calendar.MONTH, -2)

        val firstDayOfMonth = firstDayOfWeekInMonth(currentCalendar)
        println("1 first day of month = " + firstDayOfMonth)

        currentCalendar.set(Calendar.DAY_OF_MONTH, 2)
        println("2 date of first day of month = " + DateUtils.formatItDate(currentCalendar.time))

        val dayOfWeek = DateUtils.formatDate(DateUtils.dayOfWeekFormat, currentCalendar.time)
        println("3 dayOfWeek = " + dayOfWeek)

        val actualMaximum = currentCalendar.getActualMaximum(Calendar.DATE)
        println("4 actualMaximum=$actualMaximum")

    }

    fun firstDayOfWeekInMonth(calendar: GregorianCalendar): Int {
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

    @Test
    fun day_in_month(){
        val helper = MonthDisplayHelper(2022, 4, Calendar.MONDAY)
        println("year=${helper.year}")
        println("month=${helper.month}")
        println("numberOfDaysInMonth=${helper.numberOfDaysInMonth}")
        println("offset=${helper.offset}")
        println("weekStartDay=${helper.weekStartDay}")
        println("getColumnOf=${helper.getColumnOf(10)}") //2
    }
}