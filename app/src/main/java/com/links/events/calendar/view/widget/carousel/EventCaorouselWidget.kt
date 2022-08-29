package com.links.events.calendar.view.widget.carousel

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.links.events.calendar.R
import com.links.events.calendar.model.DeadlineModel
import kotlinx.android.synthetic.main.widget_event_carousel.view.*

/**
 * Created by Antonio Vitiello on 26/08/2022.
 */
class EventCaorouselWidget : LinearLayoutCompat {
    private lateinit var adapter: EventCaorouselAdapter
    private lateinit var layoutManager: LinearLayoutManager
    private var selectedItemPosition = 0

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        View.inflate(context, R.layout.widget_event_carousel, this)
        initView()
    }

    private fun initView() {
        orientation = VERTICAL
        adapter = EventCaorouselAdapter { deadline: DeadlineModel ->
            onDaySelected(deadline)
        }
        carouselRecycler.adapter = adapter
        layoutManager = carouselRecycler.layoutManager as LinearLayoutManager
        carouselRecycler.addItemDecoration(CirclePagerIndicatorDecoration(context))
        PagerSnapHelper().attachToRecyclerView(carouselRecycler)

        //On carousel scroll do action
        carouselRecycler.addOnScrollListener(CarouselScrollListener())
    }

    /**
     * Set content deadlines
     */
    fun switchData(deadlines: List<DeadlineModel>) {
        adapter.switchData(deadlines)
        carouselRecycler.scrollToPosition(0)
    }

    /**
     * do action on a day selected
     */
    private fun onDaySelected(deadline: DeadlineModel) {
        // Toast.makeText(context, deadline.desc, Toast.LENGTH_SHORT).show()
    }

    /**
     * do action on scroll changed
     */
    private fun onScrollChanged(position: Int) {
        // val deadline = adapter.deadlineOf(position)
        // Toast.makeText(context, "Carousel scroll: ${deadline.date}", Toast.LENGTH_SHORT).show()
    }


    private inner class CarouselScrollListener : RecyclerView.OnScrollListener() {
        var initialPosition = RecyclerView.NO_POSITION
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            when (newState) {
                RecyclerView.SCROLL_STATE_DRAGGING -> initialPosition = layoutManager.findLastVisibleItemPosition()
                RecyclerView.SCROLL_STATE_IDLE -> {
                    val activePosition = layoutManager.findFirstCompletelyVisibleItemPosition()
                    if (activePosition != RecyclerView.NO_POSITION && activePosition != initialPosition) {
                        initialPosition = activePosition
                        onScrollChanged(activePosition)
                    }
                }
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            selectedItemPosition = layoutManager.findFirstVisibleItemPosition()
        }
    }

}