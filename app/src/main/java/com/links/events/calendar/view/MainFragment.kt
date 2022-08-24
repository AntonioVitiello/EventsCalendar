package com.links.events.calendar.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.links.events.calendar.R
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

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
    }

    private fun testAddDeadline() {
        calendarWidget.addDeadline("2022/07/20") // ignored becouse not in this month
        calendarWidget.addDeadline("2022/08/20")
        calendarWidget.addDeadline("2022/09/20") // ignored becouse not in this month
    }

    private fun testAddDeadlines() {
        val deadlines = listOf("2022/08/7", "2022/08/8", "2022/08/8", "2022/08/25", "2022/07/25", "2022/09/25")
        calendarWidget.addAllDeadlines(deadlines)
    }

}