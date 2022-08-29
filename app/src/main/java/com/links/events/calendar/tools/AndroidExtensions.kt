@file:Suppress("unused")

package com.links.events.calendar.tools

import android.content.Context
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import io.reactivex.Single
import java.lang.ref.WeakReference
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by Antonio Vitiello
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

fun FragmentActivity.closeKeyboard() {
    val viewFocus = currentFocus ?: return
    val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(viewFocus.applicationWindowToken, 0)
}

fun <T> Single<T>.manageProgress(weakActivity: WeakReference<FragmentActivity>): Single<T> {
    return compose { upstream ->
        upstream.doOnSubscribe {
            Utils.showLoading(weakActivity)
        }
            .doOnDispose {
                Utils.hideLoading(weakActivity)
            }
            .doOnError {
                Utils.hideLoading(weakActivity)
            }
            .doOnSuccess {
                Utils.hideLoading(weakActivity)
            }
    }
}

fun Int.zeroPad(): String {
    return if (this < 10) {
        "0$this"
    } else {
        this.toString()
    }
}