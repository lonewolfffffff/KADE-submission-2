package com.otto.paulus.footballmatchschedule.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.otto.paulus.footballmatchschedule.layout.TeamUI
import com.otto.paulus.footballmatchschedule.model.Team
import com.squareup.picasso.Picasso
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find

class MainActivityAdapter(private val teams: List<Team>): RecyclerView.Adapter<MainActivityAdapter.TeamViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        return TeamViewHolder(TeamUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int = teams.size

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bindItem(teams[position])
    }

    class TeamViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val teamBadge: ImageView = view.find(TeamUI.badge)
        private val teamName: TextView = view.find(TeamUI.name)

        fun bindItem(teams: Team) {
            Picasso.get().load(teams.teamBadge).into(teamBadge)
            teamName.text = teams.teamName
        }
    }
}

