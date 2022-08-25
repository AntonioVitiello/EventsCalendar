package com.links.events.calendar.viewmodel

import android.app.Application
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.links.events.calendar.model.DeadlineModel
import com.links.events.calendar.repository.MainRepository
import com.links.events.calendar.tools.SingleEvent
import com.links.events.calendar.tools.manageProgress
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.lang.ref.WeakReference

/**
 * Created by Antonio Vitiello on 25/08/2022.
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
                .subscribe({ response ->
                    _deadlinesLiveData.postValue(SingleEvent(response))
                }, {
                    _errorLiveData.postValue(SingleEvent(true))
                })
        )
    }
}