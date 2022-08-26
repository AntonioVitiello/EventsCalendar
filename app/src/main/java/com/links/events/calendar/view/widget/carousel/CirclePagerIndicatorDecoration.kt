package com.links.events.calendar.view.widget.carousel

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.links.events.calendar.R

/**
 * Created by Antonio Vitiello
 */
class CirclePagerIndicatorDecoration(context: Context) : ItemDecoration() {
    /**
     * Active circle color
     */
    private val mColorActive: Int

    /**
     * Inactive circle color
     */
    private val mColorInactive: Int

    /**
     * Height of the space the indicator takes up at the bottom of the view.
     */
    private val mIndicatorHeight: Int

    /**
     * Indicator width.
     */
    private val mIndicatorItemDiameter: Float

    /**
     * Padding between indicators.
     */
    private val mIndicatorItemPadding: Float

    //    private final Interpolator mInterpolator = new AccelerateDecelerateInterpolator();
    private val mPaint = Paint()


    companion object {
        private val DISPLAY_DENSITY = Resources.getSystem().displayMetrics.density
    }

    init {
        mPaint.style = Paint.Style.FILL
        mPaint.isAntiAlias = true
        mColorActive = ContextCompat.getColor(context, R.color.red_light)
        mColorInactive = ContextCompat.getColor(context, R.color.red_light_20)
        mIndicatorHeight = (DISPLAY_DENSITY * context.resources.getInteger(R.integer.circle_pager_indicator_height)).toInt()
        mIndicatorItemDiameter = DISPLAY_DENSITY * context.resources.getInteger(R.integer.circle_pager_indicator_diameter)
        mIndicatorItemPadding = DISPLAY_DENSITY * context.resources.getInteger(R.integer.circle_pager_indicator_padding)
    }

    override fun onDrawOver(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(canvas, parent, state)
        val itemCount = parent.adapter?.itemCount ?: 0

        // center horizontally, calculate width and subtract half from center
        val totalLength = mIndicatorItemDiameter * itemCount
        val paddingBetweenItems = Math.max(0, itemCount - 1) * mIndicatorItemPadding
        val indicatorTotalWidth = totalLength + paddingBetweenItems
        val indicatorStartX = (parent.width - indicatorTotalWidth) / 2f

        // center vertically in the allotted space
        val indicatorPosY = parent.height - mIndicatorHeight / 2f
        drawInactiveIndicators(canvas, indicatorStartX, indicatorPosY, itemCount)

        // find active page (which should be highlighted)
        val layoutManager = parent.layoutManager as LinearLayoutManager
        val activePosition = layoutManager.findFirstVisibleItemPosition()
        if (activePosition == RecyclerView.NO_POSITION) {
            return
        }
        // boolean isInIdleState = parent.getScrollState() == RecyclerView.SCROLL_STATE_IDLE;
        drawActiveIndicator(canvas, indicatorStartX, indicatorPosY, activePosition)
    }

    private fun drawInactiveIndicators(canvas: Canvas, indicatorStartX: Float, indicatorPosY: Float, itemCount: Int) {
        mPaint.color = mColorInactive

        // width of item indicator including padding
        val itemWidth = mIndicatorItemDiameter + mIndicatorItemPadding
        var start = indicatorStartX
        for (i in 0 until itemCount) {
            // draw a circle for every item
            canvas.drawCircle(start, indicatorPosY, mIndicatorItemDiameter / 2f, mPaint)
            start += itemWidth
        }
    }

    private fun drawActiveIndicator(canvas: Canvas, indicatorStartX: Float, indicatorPosY: Float, highlightPosition: Int) {
        mPaint.color = mColorActive

        // width of item indicator including padding
        val itemWidth = mIndicatorItemDiameter + mIndicatorItemPadding
        val highlightStart = indicatorStartX + itemWidth * highlightPosition
        canvas.drawCircle(highlightStart, indicatorPosY, (mIndicatorItemDiameter + 6f) / 2f, mPaint)
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.bottom = mIndicatorHeight
    }

}