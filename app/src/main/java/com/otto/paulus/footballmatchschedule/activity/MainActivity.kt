package com.otto.paulus.footballmatchschedule.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import com.google.gson.Gson
import com.otto.paulus.footballmatchschedule.R
import com.otto.paulus.footballmatchschedule.adapter.MainActivityAdapter
import com.otto.paulus.footballmatchschedule.api.ApiRepository
import com.otto.paulus.footballmatchschedule.layout.MainActivityUI
import com.otto.paulus.footballmatchschedule.model.Event
import com.otto.paulus.footballmatchschedule.model.Team
import com.otto.paulus.footballmatchschedule.presenter.MainActivityPresenter
import com.otto.paulus.footballmatchschedule.util.*
import com.otto.paulus.footballmatchschedule.view.MainActivityView
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.onRefresh

class MainActivity : AppCompatActivity(), MainActivityView, AnkoLogger {
    private val leagueId:Int = 4328

    private var teams: MutableList<Team> = mutableListOf()
    private var events: MutableList<Event> = mutableListOf()
    //private var nextEvents: MutableList<Event> = mutableListOf()
    private lateinit var presenter: MainActivityPresenter
    private lateinit var adapter: MainActivityAdapter

    private val mainActivity: MainActivityUI = MainActivityUI()

    //private lateinit var leagueName: String
    val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        debug(item)
        when (item.itemId) {
            R.id.navigate_prev_match -> {
                debug("previous match")
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigate_next_match -> {
                debug("next match")
                return@OnNavigationItemSelectedListener true
            }
        }
        return@OnNavigationItemSelectedListener true
    }

    override fun showLoading() {
        mainActivity.progressBar.visible()
    }

    override fun hideLoading() {
        mainActivity.progressBar.invisible()
    }

    override fun showTeamList(data: List<Team>) {
        mainActivity.swipeRefresh.isRefreshing = false
        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun showLastEventList(data: List<Event>) {
        mainActivity.swipeRefresh.isRefreshing = false
        events.clear()
        events.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun showNextEventList(data: List<Event>) {
        mainActivity.swipeRefresh.isRefreshing = false
        events.clear()
        events.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainActivity.setContentView(this)
        setContentView(R.layout.fragment_main)

        mainActivity.bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)


        val request = ApiRepository()
        val gson = Gson()
        presenter = MainActivityPresenter(this, request, gson)

        /*
        val spinnerItems = resources.getStringArray(R.array.league)
        val spinnerAdapter = ArrayAdapter(ctx, R.layout.support_simple_spinner_dropdown_item, spinnerItems)
        mainActivity.spinner.adapter = spinnerAdapter

        mainActivity.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                leagueName = mainActivity.spinner.selectedItem.toString()
                presenter.getTeamList(leagueName)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        */


        adapter = MainActivityAdapter(events) {
            info(events.indexOf(it))
        }
        mainActivity.listLastEvents.adapter = adapter
        presenter.getLast15EventsList(leagueId)
        //mainActivity.listNextEvents.adapter = adapter

        mainActivity.swipeRefresh.onRefresh {
            presenter.getLast15EventsList(leagueId)
        }


    }
}
