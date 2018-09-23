package com.otto.paulus.footballmatchschedule.view

import com.otto.paulus.footballmatchschedule.model.Event

interface MatchDetailView {
    fun showLoading()
    fun hideLoading()
    fun showDetailEvent(data: Event)
}