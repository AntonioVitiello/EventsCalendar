package com.links.events.calendar.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.links.events.calendar.R
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
            val nextEvents = viewModel.nextEventsOf(deadline)
            carouselWidget.switchData(nextEvents)
            carouselWidget.isVisible = nextEvents.isNotEmpty()
        }

        // On month change load new deadlines
        calendarWidget.setMonthChangeListener { date: String ->
            viewModel.loadDeadLinesByDate(WeakReference(activity), date)
            blackboardWidget.clean(date)
        }
    }

    private fun fillData(event: SingleEvent<List<DeadlineModel>>) {
        event.getContentIfNotHandled()?.let { deadlines ->
            calendarWidget.switchDeadlines(deadlines)
        }
    }

}