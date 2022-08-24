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
    private var today = false
    var dayOfMonth = false
        private set


    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        View.inflate(context, R.layout.widget_day_event_calendar, this)
        initView()
    }

    //TODO:AV 23/08/2022 CLEANME
    private fun initView() {
        if (isInEditMode) {
            dayOfMonth = true
            today = true
            daySelected = true
        }
    }

    fun isToday(): Boolean {
        return today
    }

    fun setToday(value: Boolean) {
        if (value) {
            val color = ContextCompat.getColor(context, R.color.red_light)
            dateText.setTextColor(color)
            daySelected = true
        }
    }

    var daySelected: Boolean
        get() = false
        set(value) {
            showCircle(
                if (value) {
                    CircleType.EMPTY_CIRCLE
                } else {
                    CircleType.NONE
                }
            )
        }

    fun setDate(text: String? = null, type: DateType = DateType.NOT_IN_MONTH, activeDay: Boolean = false) {
        dateText.text = text
        if (text.isNullOrEmpty()) {
            showCircle(CircleType.NONE)
        } else {
            dayOfMonth = activeDay
            val styleRes = when (type) {
                DateType.CURRENT_WITH_EVENT -> {
                    showCircle(CircleType.DOUBLE_CIRCLE)
                    R.style.white_bold_14
                }
                DateType.CURRENT_WITHOUT_EVENT -> {
                    showCircle(CircleType.EMPTY_CIRCLE)
                    R.style.white_bold_14
                }
                DateType.WITH_EVENT -> {
                    showCircle(CircleType.CIRCLE)
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
            CircleType.EMPTY_CIRCLE -> {
                ContextCompat.getDrawable(context, R.drawable.shape_circle_empty_red_light)
            }
            CircleType.DOUBLE_CIRCLE -> {
                ContextCompat.getDrawable(context, R.drawable.shape_circle_double_red)
            }
            CircleType.CIRCLE -> {
                ContextCompat.getDrawable(context, R.drawable.shape_circle_red20)
            }
            CircleType.NONE -> {
                null
            }
        }
        circleImage.setImageDrawable(drawable)
    }


    enum class CircleType { EMPTY_CIRCLE, DOUBLE_CIRCLE, CIRCLE, NONE }
    enum class DateType { WITH_EVENT, WITHOUT_EVENT, CURRENT_WITH_EVENT, CURRENT_WITHOUT_EVENT, NOT_IN_MONTH }

}