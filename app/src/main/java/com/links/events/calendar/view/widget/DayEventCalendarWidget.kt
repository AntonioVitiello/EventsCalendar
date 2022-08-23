package com.links.events.calendar.view.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.links.events.calendar.R
import kotlinx.android.synthetic.main.widget_day_event_calendar.view.*

/**
 * Created by Antonio Vitiello
 */
class DayEventCalendarWidget : FrameLayout {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        View.inflate(context, R.layout.widget_day_event_calendar, this)
    }

    fun setText(text: String) {
        dateText.text = text
    }

    fun setActiveDate(activated: Boolean) {
        isActivated = activated
        if (!activated) {
            showCircle(CircleType.NONE)
        }
    }

    fun showDarkCircle() {
        showCircle(CircleType.DARK_RED)
    }

    fun showMediumCircle() {
        showCircle(CircleType.MEDIUM_RED)
    }

    fun showLightCircle() {
        showCircle(CircleType.LIGHT_RED)
    }

    fun showEmptyCircle() {
        showCircle(CircleType.EMPTY_CIRCLE)
    }

    fun hideCircle() {
        showCircle(CircleType.NONE)
    }

    fun showCircle(type: CircleType) {
        val drawable = when (type) {
            CircleType.DARK_RED -> {
                ContextCompat.getDrawable(context, R.drawable.shape_dark_red_circle)
            }
            CircleType.MEDIUM_RED -> {
                ContextCompat.getDrawable(context, R.drawable.shape_medium_red_circle)
            }
            CircleType.LIGHT_RED -> {
                ContextCompat.getDrawable(context, R.drawable.shape_light_red_circle)
            }
            CircleType.EMPTY_CIRCLE -> {
                ContextCompat.getDrawable(context, R.drawable.shape_red_empty_circle)
            }
            CircleType.NONE -> {
                null
            }
        }
        circleImage.setImageDrawable(drawable)
    }


    enum class CircleType { DARK_RED, MEDIUM_RED, LIGHT_RED, EMPTY_CIRCLE, NONE }

}