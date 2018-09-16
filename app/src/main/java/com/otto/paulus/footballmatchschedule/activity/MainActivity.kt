package com.otto.paulus.footballmatchschedule.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.google.gson.Gson
import com.otto.paulus.footballmatchschedule.R
import com.otto.paulus.footballmatchschedule.adapter.MainActivityAdapter
import com.otto.paulus.footballmatchschedule.api.ApiRepository
import com.otto.paulus.footballmatchschedule.layout.MainActivityUI
import com.otto.paulus.footballmatchschedule.model.Team
import com.otto.paulus.footballmatchschedule.presenter.MainActivityPresenter
import com.otto.paulus.footballmatchschedule.util.*
import com.otto.paulus.footballmatchschedule.view.MainActivityView
import org.jetbrains.anko.ctx
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.support.v4.onRefresh

class MainActivity : AppCompatActivity(), MainActivityView {
    private var teams: MutableList<Team> = mutableListOf()
    private lateinit var presenter: MainActivityPresenter
    private lateinit var adapter: MainActivityAdapter

    private val mainActivity: MainActivityUI = MainActivityUI()

    private lateinit var leagueName: String

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainActivity.setContentView(this)

        adapter = MainActivityAdapter(teams)
        mainActivity.listTeam.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = MainActivityPresenter(this, request, gson)

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

        mainActivity.swipeRefresh.onRefresh {
            presenter.getTeamList(leagueName)
        }

    }
}
