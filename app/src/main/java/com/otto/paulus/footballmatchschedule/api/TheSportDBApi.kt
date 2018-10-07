package com.otto.paulus.footballmatchschedule.api

import android.net.Uri
import com.otto.paulus.footballmatchschedule.BuildConfig
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

object TheSportDBApi:AnkoLogger {
    fun getLast15Events(leagueId: Int?): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath(BuildConfig.TSDB_API_KEY)
                .appendPath("eventspastleague.php")
                .appendQueryParameter("id", leagueId.toString())
                .build()
                .toString()
    }

    fun getNext15Events(leagueId: Int?): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath(BuildConfig.TSDB_API_KEY)
                .appendPath("eventsnextleague.php")
                .appendQueryParameter("id", leagueId.toString())
                .build()
                .toString()
    }

    fun getEventDetail(eventId: String): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath(BuildConfig.TSDB_API_KEY)
                .appendPath("lookupevent.php")
                .appendQueryParameter("id", eventId.toString())
                .build()
                .toString()
    }

    fun getTeamDetail(teamId: String): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath(BuildConfig.TSDB_API_KEY)
                .appendPath("lookupteam.php")
                .appendQueryParameter("id", teamId.toString())
                .build()
                .toString()
    }
}