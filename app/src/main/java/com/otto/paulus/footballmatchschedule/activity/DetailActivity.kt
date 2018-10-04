package com.otto.paulus.footballmatchschedule.activity

import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v7.app.AppCompatActivity
import com.google.gson.Gson
import com.otto.paulus.footballmatchschedule.R
import com.otto.paulus.footballmatchschedule.api.ApiRepository
import com.otto.paulus.footballmatchschedule.model.Event
import com.otto.paulus.footballmatchschedule.model.EventDetail
import com.otto.paulus.footballmatchschedule.model.Team
import com.otto.paulus.footballmatchschedule.presenter.MatchDetailPresenter
import com.otto.paulus.footballmatchschedule.util.invisible
import com.otto.paulus.footballmatchschedule.util.parse
import com.otto.paulus.footballmatchschedule.util.visible
import com.otto.paulus.footballmatchschedule.view.MatchDetailView
import com.squareup.picasso.Picasso
import org.jetbrains.anko.AnkoLogger
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.info
import java.lang.Math.abs

class DetailActivity: AppCompatActivity(), MatchDetailView, AppBarLayout.OnOffsetChangedListener, AnkoLogger {
    private val presenter: MatchDetailPresenter = MatchDetailPresenter(this, ApiRepository(), Gson())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        //setSupportActionBar(toolbar)

        presenter.getEventDetail(441613)

        presenter.getTeamDetail(133602)
        presenter.getTeamDetail(133614,false)

        appBar.addOnOffsetChangedListener(this)
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
        info("Offset: "+verticalOffset+" -> "+(verticalOffset+200)/-40f)
        animateToolbar(verticalOffset)
    }

    private fun animateToolbar(verticalOffset: Int) {
        when(verticalOffset) {
            // fade ins
            in -240..-200 -> {
                ivHomeBadgeToolbar.visible()
                ivHomeBadgeToolbar.animate().alpha((200 - verticalOffset)/40f)
                ivHomeBadgeToolbar.animate().scaleX((verticalOffset+200)/-40f)
                ivHomeBadgeToolbar.animate().scaleY((verticalOffset+200)/-40f)
                tvScoreToolbar.visible()
                tvScoreToolbar.animate().alpha((200 - verticalOffset)/40f)
                tvScoreToolbar.animate().scaleX((verticalOffset+200)/-40f)
                tvScoreToolbar.animate().scaleY((verticalOffset+200)/-40f)
                ivAwayBadgeToolbar.visible()
                ivAwayBadgeToolbar.animate().alpha((200 - verticalOffset)/40f)
                ivAwayBadgeToolbar.animate().scaleX((verticalOffset+200)/-40f)
                ivAwayBadgeToolbar.animate().scaleY((verticalOffset+200)/-40f)
            }
            in -119..0 -> {
                ivHomeBadgeToolbar.alpha = 0f
                ivHomeBadgeToolbar.invisible()
                tvScoreToolbar.alpha = 0f
                tvScoreToolbar.invisible()
                ivAwayBadgeToolbar.alpha = 0f
                ivAwayBadgeToolbar.invisible()
            }
        }
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showDetailEvent(match: EventDetail) {
        tvHomeTeamName.text = match.homeTeam
        tvScore.text = match.homeScore + " - " + match.awayScore
        tvScoreToolbar.text = match.homeScore + " - " + match.awayScore
        tvAwayTeamName.text = match.awayTeam

        tvHomeFormation.text = match.homeFormation
        tvAwayFormation.text = match.awayFormation

        tvHomeGoals.text = match.homeGoalDetails?.parse()
        tvAwayGoals.text = match.awayGoalDetails?.parse()

        tvHomeShots.text = match.homeShots
        tvAwayShots.text = match.awayShots

        tvHomeGoalKeeper.text = match.homeGoalKeeper
        tvAwayGoalKeeper.text = match.awayGoalKeeper

        tvHomeDefenders.text = match.homeDefense?.parse()
        tvAwayDefenders.text = match.awayDefense?.parse()

        tvHomeMidfielders.text = match.homeMidfield?.parse()
        tvAwayMidfielders.text = match.awayMidfield?.parse()

        tvHomeForwards.text = match.homeForward?.parse()
        tvAwayForwards.text = match.awayForward?.parse()

        tvHomeSubstitutes.text = match.homeSubstitutes?.parse()
        tvAwaySubstitutes.text = match.awaySubstitutes?.parse()
    }

    override fun showDetailTeam(data: Team, isHomeTeam: Boolean) {
        Picasso.get().load(data.teamBadge).into(if(isHomeTeam) ivHomeBadge else ivAwayBadge)
        Picasso.get().load(data.teamBadge).into(if(isHomeTeam) ivHomeBadgeToolbar else ivAwayBadgeToolbar)
    }
}