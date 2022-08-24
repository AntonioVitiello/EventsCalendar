package com.links.events.calendar.tools

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Antonio Vitiello on 30/01/2021.
 */
@SuppressLint("ConstantLocale")
class DateUtils {

    companion object {
        val monthYearDateFormat by lazy(LazyThreadSafetyMode.NONE) { SimpleDateFormat("MMMM yyyy", Locale.ITALY) }
        val dayOfWeekFormat by lazy(LazyThreadSafetyMode.NONE) { SimpleDateFormat("EEE", Locale.ITALY) }
        val itDateFormat by lazy(LazyThreadSafetyMode.NONE) { SimpleDateFormat("EEEE dd MMMM yyyy", Locale.ITALY) }
        val iso8601DateFormat by lazy(LazyThreadSafetyMode.NONE) { SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ITALY) }

        @JvmStatic
        fun formatDate(dateFormat: SimpleDateFormat, date: Date): String {
            return date.format(dateFormat)
        }

        fun parseDateOrNull(dateFormat: SimpleDateFormat, string: String?): Date? {
            return string?.parseDateOrNull(dateFormat)
        }

        //"MMMM yyyy" eg: Agosto 2022
        fun formatMonthYear(monthYearDate: Date = Date()): String {
            return formatDate(monthYearDateFormat, monthYearDate).capitalize()
        }

        fun parseMonthYear(monthYear: String): Date {
            return parseDateOrNull(monthYearDateFormat, monthYear) ?: Date()
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