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
import com.otto.paulus.footballmatchschedule.model.Event
import com.otto.paulus.footballmatchschedule.presenter.MatchListPresenter
import com.otto.paulus.footballmatchschedule.util.*
import com.otto.paulus.footballmatchschedule.view.MatchListView
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.support.v4.onRefresh

private const val ARG_LIST_PREV_MATCH = "prevMatch"

class MatchListFragment : Fragment(),MatchListView, AnkoLogger {
    private val leagueId:Int = 4328

    private var isPrevMatch: Boolean = true
    private var listener: OnFragmentInteractionListener? = null

    private var events: MutableList<Event> = mutableListOf()

    private lateinit var presenter: MatchListPresenter
    private lateinit var adapter: MatchListAdapter

    private lateinit var eventsList: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            isPrevMatch = it.getBoolean(ARG_LIST_PREV_MATCH)
        }
        presenter = MatchListPresenter(this, ApiRepository(), Gson())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_matchlist, container, false)

        progressBar = rootView.findViewById(R.id.progressBar)
        swipeRefresh = rootView.findViewById(R.id.swipeRefreshLayout)
        swipeRefresh.onRefresh {
            getEventsList()
        }

        adapter = MatchListAdapter(events) {
            val match = events.get(events.indexOf(it))
            listener?.onMatchListItemClick(match);
        }
        eventsList = rootView.findViewById(R.id.rvMatchList)
        eventsList.adapter = adapter

        return rootView

    }

    private fun getEventsList() {
        if(isPrevMatch) {
            presenter.getLast15EventsList(leagueId)
        }
        else {
            presenter.getNext15EventsList(leagueId)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getEventsList()
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
        fun onMatchListItemClick(match:Event)
    }

    companion object {
        @JvmStatic
        fun newInstance(isPrevMatch: Boolean) =
                MatchListFragment().apply {
                    arguments = Bundle().apply {
                        putBoolean(ARG_LIST_PREV_MATCH, isPrevMatch)
                    }
                }
    }
}
