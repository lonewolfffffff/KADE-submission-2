package com.otto.paulus.footballmatchschedule.model

import com.google.gson.annotations.SerializedName

data class Event (
    @SerializedName("idEvent")
    var eventId: String? = null,

    @SerializedName("strDate")
    var eventDate: String? = null,

    // Home
    @SerializedName("strHomeTeam")
    var homeTeam: String? = null,

    @SerializedName("intHomeScore")
    var homeScore: String? = null,

    // Away
    @SerializedName("strAwayTeam")
    var awayTeam: String? = null,

    @SerializedName("intAwayScore")
    var awayScore: String? = null
)
