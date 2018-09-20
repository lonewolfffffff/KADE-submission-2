package com.otto.paulus.footballmatchschedule.view

import com.otto.paulus.footballmatchschedule.model.Event
import com.otto.paulus.footballmatchschedule.model.Team

interface MainActivityView {
    fun showLoading()
    fun hideLoading()
    fun showNextEventList(data: List<Event>)
    fun showLastEventList(data: List<Event>)
}