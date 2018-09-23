package com.otto.paulus.footballmatchschedule.activity.fragment

import android.content.Context
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.google.gson.Gson
import com.otto.paulus.footballmatchschedule.R
import com.otto.paulus.footballmatchschedule.adapter.MatchListAdapter
import com.otto.paulus.footballmatchschedule.api.ApiRepository
import com.otto.paulus.footballmatchschedule.layout.MatchListUI
import com.otto.paulus.footballmatchschedule.model.Event
import com.otto.paulus.footballmatchschedule.presenter.MatchListPresenter
import com.otto.paulus.footballmatchschedule.util.*
import com.otto.paulus.footballmatchschedule.view.MatchListView
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.find
import org.jetbrains.anko.info
import org.jetbrains.anko.support.v4.onRefresh
import kotlinx.android.synthetic.main.fragment_matchlist.*

class MatchListFragment : Fragment(),MatchListView, AnkoLogger {
    private val leagueId:Int = 4328

    private var listener: OnFragmentInteractionListener? = null

    private var events: MutableList<Event> = mutableListOf()

    private lateinit var presenter: MatchListPresenter
    private lateinit var adapter: MatchListAdapter

    private lateinit var eventsList: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = MatchListPresenter(this, ApiRepository(), Gson())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val matchListUI = MatchListUI()
        val matchList = matchListUI.createView(AnkoContext.create(inflater.context,this,false))
        val bottomNavigation:BottomNavigationView = matchList.find(matchListUI.bottomNavigation.id)

        bottomNavigation.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->

            when (item.itemId) {
                R.id.navigate_prev_match -> {
                    presenter.getLast15EventsList(leagueId)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigate_next_match -> {
                    presenter.getNext15EventsList(leagueId)
                    return@OnNavigationItemSelectedListener true
                }
            }
            return@OnNavigationItemSelectedListener false
        })

        progressBar = matchList.find(matchListUI.progressBar.id)
        swipeRefresh = matchList.find(matchListUI.swipeRefresh.id)
        swipeRefresh.onRefresh {

        }
        eventsList = matchList.find(matchListUI.eventsList.id)

        adapter = MatchListAdapter(events) {
            val match = events.get(events.indexOf(it))
            listener?.onMatchListFragmentInteraction(match.eventId);
        }
        eventsList.adapter = adapter

        return matchList
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.getLast15EventsList(leagueId)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showEventList(data: List<Event>) {
        swipeRefresh.isRefreshing = false
        events.clear()
        events.addAll(data)
        adapter.notifyDataSetChanged()
    }

    interface OnFragmentInteractionListener {
        fun onMatchListFragmentInteraction(matchId:String?)
    }

    companion object {
        @JvmStatic
        fun newInstance() = MatchListFragment()
    }
}
