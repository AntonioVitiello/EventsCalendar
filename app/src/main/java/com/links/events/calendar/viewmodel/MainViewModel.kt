package com.links.events.calendar.viewmodel

import android.app.Application
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.links.events.calendar.model.DeadlineModel
import com.links.events.calendar.repository.MainRepository
import com.links.events.calendar.tools.DateUtils
import com.links.events.calendar.tools.SingleEvent
import com.links.events.calendar.tools.manageProgress
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.lang.ref.WeakReference
import java.util.Collections.addAll
import java.util.Collections.list

/**
 * Created by Antonio Vitiello
 */
class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val disposable = CompositeDisposable()
    private val repository = MainRepository()
    val _deadlinesLiveData = MutableLiveData<SingleEvent<List<DeadlineModel>>>()
    val deadlinesLiveData = _deadlinesLiveData
    val _errorLiveData = MutableLiveData<SingleEvent<Boolean>>()
    val errorLiveData = _errorLiveData


    fun loadDeadLinesByDate(weakActivity: WeakReference<FragmentActivity>, data: String) {
        disposable.add(
            repository.loadDeadLinesByDate(data)
                .manageProgress(weakActivity)
                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
                .map { deadlines ->
                    deadlines.forEach { deadline ->
                        DateUtils.parseDayOfYearOrNull(deadline.date)?.let { dayOfYearDate ->
                            deadline.date = DateUtils.formatDayOfYear(dayOfYearDate)
                        }
                    }
                    deadlines.sortedBy { it.date }
                }
                .subscribe({ response ->
                    _deadlinesLiveData.postValue(SingleEvent(response))
                }, {
                    _errorLiveData.postValue(SingleEvent(true))
                })
        )
    }

    fun nextEventsOf(deadline: DeadlineModel): List<DeadlineModel> {
        return mutableListOf<DeadlineModel>().apply {
            _deadlinesLiveData.value?.peekContent()?.let { deadlines ->
                val indexOfDeadline = deadlines.indexOfFirst { it == deadline }
                if (indexOfDeadline != -1 && indexOfDeadline < deadlines.size - 1) {
                    val startIndex = indexOfDeadline + 1
                    val endIndex = (indexOfDeadline + 3).coerceAtMost(deadlines.size - 1)
                    val nextEvents = deadlines.subList(startIndex, endIndex + 1)
                    addAll(nextEvents)
                }
            }
        }
    }

}