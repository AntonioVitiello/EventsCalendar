package com.links.events.calendar.view.widget.calendar

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewParent
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.core.widget.TextViewCompat
import com.links.events.calendar.R
import com.links.events.calendar.model.DeadlineModel
import com.links.events.calendar.tools.SafeClickListener
import kotlinx.android.synthetic.main.widget_day_event_calendar.view.*

/**
 * Created by Antonio Vitiello
 */
class DeadlineDayWidget : FrameLayout {
    private var _today = false
    private var _sunday = false
    private var _dayWithEvent = false
    private var _daySelected = false
    private var _eventData: DeadlineModel? = null
    var dayInMonth = false
        private set

    enum class CircleType { EMPTY_CIRCLE, DOUBLE_CIRCLE, CIRCLE, NONE }
    enum class DateType { WITH_EVENT, WITHOUT_EVENT, CURRENT_WITH_EVENT, CURRENT_WITHOUT_EVENT, NOT_IN_MONTH }


    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        View.inflate(context, R.layout.widget_day_event_calendar, this)
        initView()
    }

    private fun initView() {
        dateText.setOnClickListener(SafeClickListener {
            dayClickListenerOrNull()?.onNewSelection(this)
        })
        if (isInEditMode) {
            dayInMonth = true
            today = true
            daySelected = true
            dayWithEvent = true
        }
    }

    var today: Boolean
        get() = _today
        set(value) {
            _today = value
            if (value) {
                val color = ContextCompat.getColor(context, R.color.red_light)
                dateText.setTextColor(color)
                daySelected = true
            }
        }

    var sunday: Boolean
        get() = _sunday
        set(value) {
            _sunday = value
            if (value) {
                val color = ContextCompat.getColor(context, R.color.gray)
                dateText.setTextColor(color)
            }
        }

    var daySelected: Boolean
        get() = _daySelected
        set(value) {
            _daySelected = value
            showCircle(
                if (value) {
                    if (dayWithEvent) {
                        CircleType.DOUBLE_CIRCLE
                    } else {
                        CircleType.EMPTY_CIRCLE
                    }
                } else {
                    if (dayWithEvent) {
                        CircleType.CIRCLE
                    } else {
                        CircleType.NONE
                    }
                }
            )
        }

    var dayWithEvent: Boolean
        get() = _dayWithEvent
        set(value) {
            _dayWithEvent = value
            showCircle(
                if (value) {
                    if (daySelected) {
                        CircleType.DOUBLE_CIRCLE
                    } else {
                        CircleType.CIRCLE
                    }
                } else {
                    if (daySelected) {
                        CircleType.EMPTY_CIRCLE
                    } else {
                        CircleType.NONE
                    }
                }
            )
        }

    var eventData: DeadlineModel?
        get() = _eventData
        set(value) {
            _eventData = value
        }

    private fun resetState() {
        today = false
        dayWithEvent = false
        daySelected = false
        dayInMonth = false
        eventData = null
    }

    fun getDayOfMonth(): Int {
        return dateText.text.toString().toInt()
    }

    fun setDate(day: String? = null, type: DateType = DateType.NOT_IN_MONTH, activeDay: Boolean = false) {
        resetState()
        dateText.text = day
        if (day.isNullOrEmpty()) {
            showCircle(CircleType.NONE)
        } else {
            dayInMonth = activeDay
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

    private fun dayClickListenerOrNull(viewParent: ViewParent? = parent): IDayClick? {
        return viewParent?.let { p ->
            if (p is IDayClick) {
                p
            } else {
                dayClickListenerOrNull(p.parent)
            }
        }
    }

}