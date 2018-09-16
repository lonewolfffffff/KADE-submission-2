package com.otto.paulus.footballmatchschedule.presenter

import com.google.gson.Gson
import com.otto.paulus.footballmatchschedule.api.ApiRepository
import com.otto.paulus.footballmatchschedule.api.TheSportDBApi
import com.otto.paulus.footballmatchschedule.model.EventResponse
import com.otto.paulus.footballmatchschedule.model.TeamResponse
import com.otto.paulus.footballmatchschedule.view.MainActivityView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainActivityPresenter(private val view: MainActivityView,
                            private val apiRepository: ApiRepository,
                            private val gson: Gson) {
    fun getTeamList(league: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getTeams(league)),
                    TeamResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showTeamList(data.teams)
            }
        }
    }

    fun getNext15EventsList(leagueId: Int?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getNext15Events(leagueId)),
                    EventResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showEventList(data.events)
            }
        }
    }
}