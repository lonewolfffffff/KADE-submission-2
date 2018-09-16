package com.otto.paulus.footballmatchschedule.api

import android.net.Uri
import com.otto.paulus.footballmatchschedule.BuildConfig

object TheSportDBApi {
    val sportDBApiBuilder:Uri.Builder = Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)

    fun getTeams(league: String?): String {
        return sportDBApiBuilder
                .appendPath("search_all_teams.php")
                .appendQueryParameter("l", league)
                .build()
                .toString()
    }

    fun getLast15Events(leagueId: Int?): String {
        return sportDBApiBuilder
                .appendPath("eventspastleague.php")
                .appendQueryParameter("id", leagueId.toString())
                .build()
                .toString()
    }

    fun getNext15Events(leagueId: Int?): String {
        return sportDBApiBuilder
                .appendPath("eventsnextleague.php")
                .appendQueryParameter("id", leagueId.toString())
                .build()
                .toString()
    }

    fun getEventDetail(eventId: Int?): String {
        return sportDBApiBuilder
                .appendPath("lookupevent.php")
                .appendQueryParameter("id", eventId.toString())
                .build()
                .toString()
    }
}