package com.links.events.calendar.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.links.events.calendar.R
import com.links.events.calendar.data.source.getDeadlineByDate
import com.links.events.calendar.data.source.getMonthDeadlines
import com.links.events.calendar.model.DeadlineModel
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponents()
        testAddDeadline()
        testAddDeadlines()
    }

    private fun initComponents() {
        closeImage.setOnClickListener { requireActivity().finish() }
        calendarWidget.setDaySelectionListener { deadline: DeadlineModel ->
            //TODO:AV 24/08/2022 invoke event widget
            Toast.makeText(context, "Get deadline info: ${deadline.date}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun testAddDeadline() {
        calendarWidget.addDeadline(getDeadlineByDate("2022/07/20")) // ignored becouse not in this month
        calendarWidget.addDeadline(getDeadlineByDate("2022/08/20"))
        calendarWidget.addDeadline(getDeadlineByDate("2022/09/20")) // ignored becouse not in this month
    }

    private fun testAddDeadlines() {
        calendarWidget.addAllDeadlines(getMonthDeadlines())
    }

}