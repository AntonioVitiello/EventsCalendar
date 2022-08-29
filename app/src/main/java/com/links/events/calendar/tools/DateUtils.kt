package com.links.events.calendar.tools

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Antonio Vitiello
 */
@SuppressLint("ConstantLocale")
class DateUtils {

    companion object {
        val monthYearDateFormat by lazy(LazyThreadSafetyMode.NONE) { SimpleDateFormat("MMMM yyyy", Locale.ITALY) }
        val dayOfWeekFormat by lazy(LazyThreadSafetyMode.NONE) { SimpleDateFormat("EEE", Locale.ITALY) }
        val dayOfYearFormat by lazy(LazyThreadSafetyMode.NONE) { SimpleDateFormat("yyyy/MM/dd", Locale.ITALY) }
        val dayMonthFormat by lazy(LazyThreadSafetyMode.NONE) { SimpleDateFormat("dd MMMM", Locale.ITALY) }
        val dayOfYearWithMonthNameFormat by lazy(LazyThreadSafetyMode.NONE) { SimpleDateFormat("dd MMMM yyyy", Locale.ITALY) }
        val itDateFormat by lazy(LazyThreadSafetyMode.NONE) { SimpleDateFormat("EEEE dd MMMM yyyy", Locale.ITALY) }
        val iso8601DateFormat by lazy(LazyThreadSafetyMode.NONE) { SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ITALY) }

        fun formatDate(dateFormat: SimpleDateFormat, date: Date): String {
            return date.format(dateFormat)
        }

        fun parseDateOrNull(dateFormat: SimpleDateFormat, string: String?): Date? {
            return string?.parseDateOrNull(dateFormat)
        }

        //"MMMM yyyy" eg: Agosto 2022
        fun formatMonthYear(monthYearDate: Date = Date(), capitalize: Boolean = true): String {
            val date = formatDate(monthYearDateFormat, monthYearDate)
            return if (capitalize) {
                date.capitalize()
            } else {
                date
            }
        }

        //"MMMM yyyy" eg: Agosto 2022
        fun parseMonthYear(monthYear: String): Date? {
            return parseDateOrNull(monthYearDateFormat, monthYear)
        }

        //"dd MMMM" eg: 19 agosto
        fun formatDayMonth(dayMonthDate: Date = Date()): String {
            return formatDate(dayMonthFormat, dayMonthDate)
        }

        //"dd MMMM" eg: 19 agosto
        fun parseDayMonth(dayMonth: String): Date? {
            return parseDateOrNull(dayMonthFormat, dayMonth)
        }

        //"yyyy/MM/dd" eg: 2022/08/24
        fun formatDayOfYear(dayOfYear: Date = Date()): String {
            return formatDate(dayOfYearFormat, dayOfYear)
        }

        //"yyyy/MM/dd" eg: 2022/08/24
        fun parseDayOfYearOrNull(dayOfYear: String?): Date? {
            return parseDateOrNull(dayOfYearFormat, dayOfYear)
        }

        //"dd MMMM yyyy" eg: 24 Agosto 2022
        fun formatDayOfYearWithMonthName(dayOfYear: Date = Date()): String {
            return formatDate(dayOfYearWithMonthNameFormat, dayOfYear).capitalize()
        }

        //"dd MMMM yyyy" eg: 24 Agosto 2022
        fun parseDayOfYearWithMonthName(dayOfYear: String): Date? {
            return parseDateOrNull(dayOfYearWithMonthNameFormat, dayOfYear)
        }

        //"yyyy-MM-dd'T'HH:mm:ss'Z'"
        fun formatIso8601Date(date: Date = Date()): String {
            return formatDate(iso8601DateFormat, date).capitalize()
        }

        //"EEEE dd MMMM yyyy"
        fun formatItDate(date: Date = Date()): String {
            return formatDate(itDateFormat, date).capitalize()
        }

        fun todayCalendar(): GregorianCalendar {
            val hostTimeZone = TimeZone.getDefault()
            return GregorianCalendar(hostTimeZone, Locale.ITALY).apply {
                set(Calendar.HOUR_OF_DAY, 0)
                clear(Calendar.MINUTE)
                clear(Calendar.SECOND)
                clear(Calendar.MILLISECOND)
            }
        }
    }

}