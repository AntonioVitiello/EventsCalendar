package com.links.events.calendar.tools

import android.os.SystemClock
import android.view.View

/**
 * Created by Antonio Vitiello on 20/05/2021.
 */
class SafeClickListener(
    private var defaultInterval: Int = DEFAULT_SAFE_INTERVAL_MILLIS,
    private val doOnClick: (View) -> Unit
) : View.OnClickListener {
    private var lastTimeClicked = 0L

    companion object {
        const val DEFAULT_SAFE_INTERVAL_MILLIS = 1500
    }

    override fun onClick(v: View) {
        if (SystemClock.elapsedRealtime() - lastTimeClicked < defaultInterval) {
            return
        }
        lastTimeClicked = SystemClock.elapsedRealtime()
        doOnClick.invoke(v)
    }

}