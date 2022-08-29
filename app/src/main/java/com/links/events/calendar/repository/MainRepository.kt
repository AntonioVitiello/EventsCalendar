package com.links.events.calendar.repository

import com.links.events.calendar.data.source.getDeadlinesByDate
import com.links.events.calendar.network.dto.DeadlineResponse
import io.reactivex.Single
import java.util.concurrent.TimeUnit

/**
 * Created by Antonio Vitiello
 */
class MainRepository {

    fun loadDeadLinesByDate(data: String): Single<DeadlineResponse> {
        return Single.fromCallable {
            getDeadlinesByDate(data)
        }.delay(1300, TimeUnit.MILLISECONDS)
    }

}