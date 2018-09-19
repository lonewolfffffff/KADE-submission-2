package com.otto.paulus.footballmatchschedule.adapter

import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.otto.paulus.footballmatchschedule.layout.MatchUI
import com.otto.paulus.footballmatchschedule.layout.TeamUI
import com.otto.paulus.footballmatchschedule.model.Event
import com.otto.paulus.footballmatchschedule.util.*
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.find

class MainActivityAdapter(private val events: List<Event>, private val listener: (Event) -> Unit): RecyclerView.Adapter<MainActivityAdapter.MatchViewHolder>(),AnkoLogger {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        return MatchViewHolder(MatchUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int = events.size

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bindItem(events[position], listener)
    }

    class MatchViewHolder(view: View) : RecyclerView.ViewHolder(view), AnkoLogger {
        private val eventDate: TextView = view.find(MatchUI.eventDate)
        private val homeTeamName: TextView = view.find(MatchUI.homeTeamName)
        private val homeTeamScore: TextView = view.find(MatchUI.homeTeamScore)
        private val awayTeamScore: TextView = view.find(MatchUI.awayTeamScore)
        private val awayTeamName: TextView = view.find(MatchUI.awayTeamName)

        fun bindItem(events: Event, listener: (Event) -> Unit) {
            eventDate.text = events.eventDate?.formatDate()

            homeTeamName.text = events.homeTeam
            homeTeamScore.text = events.homeScore
            awayTeamScore.text = events.awayScore
            awayTeamName.text = events.awayTeam

            val homeScore = events.homeScore?.toInt()?:0
            val awayScore = events.awayScore?.toInt()?:0

            if(homeScore-awayScore>0) {
                homeTeamName.bold()
                awayTeamName.normal()
            }
            else {
                if(homeScore-awayScore<0) {
                    homeTeamName.normal()
                    awayTeamName.bold()
                }
                else {
                    homeTeamName.normal()
                    awayTeamName.normal()
                }
            }

            itemView.setOnClickListener {
                listener(events)
            }

        }
    }
}

