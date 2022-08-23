package com.links.events.calendar.view.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.core.widget.TextViewCompat
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

    fun setDate(text: String? = null, type: DateType = DateType.NOT_IN_MONTH) {
        dateText.text = text
        if (text.isNullOrEmpty()) {
            showCircle(CircleType.NONE)
        } else {
            val styleRes = when (type) {
                DateType.CURRENT_WITH_EVENT -> {
                    showCircle(CircleType.LIGHT_RED)
                    R.style.white_bold_14
                }
                DateType.CURRENT_WITHOUT_EVENT -> {
                    showCircle(CircleType.EMPTY_CIRCLE)
                    R.style.white_bold_14
                }
                DateType.WITH_EVENT -> {
                    showCircle(CircleType.MEDIUM_RED)
                    R.style.white_bold_14
                }
                DateType.WITHOUT_EVENT -> {
                    showCircle(CircleType.NONE)
                    R.style.white_bold_14
                }
                DateType.NOT_IN_MONTH -> {
                    showCircle(CircleType.NONE)
                    R.style.gray_medium_14
                }
            }
            TextViewCompat.setTextAppearance(dateText, styleRes)
        }
    }

    fun showCircle(type: CircleType = CircleType.NONE) {
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
    enum class DateType { WITH_EVENT, WITHOUT_EVENT, CURRENT_WITH_EVENT, CURRENT_WITHOUT_EVENT, NOT_IN_MONTH }

}