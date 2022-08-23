@file:Suppress("unused")

package com.links.events.calendar.tools

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.util.Log
import androidx.fragment.app.Fragment
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by Antonio Vitiello on 25/01/2021.
 */
fun Date.format(dateFormat: SimpleDateFormat): String {
    return dateFormat.format(this)
}

fun String.parseDateOrNull(dateFormat: SimpleDateFormat): Date? {
    return if (isEmpty()) {
        null
    } else {
        try {
            dateFormat.parse(this)
        } catch (exc: ParseException) {
            Log.e("parseDateOrNull", "Error while parsing date:[$this].", exc)
            null
        }
    }
}

infix fun Int.floorMod(other: Int) = ((this % other) + other) % other

/**
 * Find implemented inteface in parentFragment, targetFragment, activity or NULL
 */
@Suppress("DEPRECATION")
inline fun <reified T> Fragment.implementedInterface(): T? {
    return when {
        parentFragment is T -> parentFragment as T
        targetFragment is T -> targetFragment as T
        activity is T -> activity as T
        else -> {
            Log.e(
                "implementedInterface",
                "Cannot trigger interface methods cause the caller doesn't implement the interface ${T::class.java}"
            )
            null
        }
    }
}

fun Int.isPair(): Boolean {
    return this % 2 == 0
}

fun Int.isOdd(): Boolean {
    return !isPair()
}

fun String.capitalize(): String {
    return replaceFirstChar {
        if (it.isLowerCase()) {
            it.titlecase(Locale.ITALY)
        } else {
            it.toString()
        }
    }
}