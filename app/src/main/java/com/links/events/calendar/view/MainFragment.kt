package com.links.events.calendar.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.links.events.calendar.R
import com.links.events.calendar.data.source.getDeadlineByDate
import com.links.events.calendar.data.source.getMonthDeadlines
import com.links.events.calendar.model.DeadlineModel
import com.links.events.calendar.tools.DateUtils
import com.links.events.calendar.tools.SingleEvent
import com.links.events.calendar.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_main.*
import java.lang.ref.WeakReference

class MainFragment : Fragment() {
    private val viewModel by activityViewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadDeadLinesByDate(WeakReference(activity), DateUtils.formatDayOfYear())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.deadlinesLiveData.observe(viewLifecycleOwner, ::fillData)

        initComponents()

//         just for testing
//        testAddDeadline()
//        testAddDeadlines()
    }

    private fun initComponents() {
        closeImage.setOnClickListener { requireActivity().finish() }

        // On day selection fill calendar events blackboard
        calendarWidget.setDaySelectionListener { deadline: DeadlineModel ->
            blackboardWidget.setDeadlines(deadline)
        }

        // On month change load new deadlines
        calendarWidget.setMonthChangeListener { date: String ->
            viewModel.loadDeadLinesByDate(WeakReference(activity), date)
            blackboardWidget.clean(date)
        }
    }

    private fun fillData(event: SingleEvent<List<DeadlineModel>>) {
        event.getContentIfNotHandled()?.let { deadlines ->
            calendarWidget.addAllDeadlines(deadlines)

            //TODO:AV 26/08/2022 CLEANME spostare e nascondere anche header con icona
            carouselWidget.switchData(deadlines)
            carouselWidget.isVisible = true
        }
    }

    // just for testing
    private fun testAddDeadline() {
        calendarWidget.addDeadline(getDeadlineByDate("2022/07/20")) // ignored becouse not in this month
        calendarWidget.addDeadline(getDeadlineByDate("2022/08/20"))
        calendarWidget.addDeadline(getDeadlineByDate("2022/09/20")) // ignored becouse not in this month
    }

    // just for testing
    private fun testAddDeadlines() {
        calendarWidget.addAllDeadlines(getMonthDeadlines())
    }

}