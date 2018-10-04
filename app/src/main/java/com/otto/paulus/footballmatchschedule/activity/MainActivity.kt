package com.otto.paulus.footballmatchschedule.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.otto.paulus.footballmatchschedule.R
import com.otto.paulus.footballmatchschedule.activity.fragment.MatchDetailFragment
import com.otto.paulus.footballmatchschedule.activity.fragment.MatchListFragment
import com.otto.paulus.footballmatchschedule.layout.MainActivityUI
import com.otto.paulus.footballmatchschedule.util.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.setContentView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MatchListFragment.OnFragmentInteractionListener, MatchDetailFragment.OnFragmentInteractionListener, AnkoLogger {
    private val mainActivity: MainActivityUI = MainActivityUI()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //supportActionBar?.hide()
        //mainActivity.setContentView(this)

        setContentView(R.layout.activity_main)
        addFragment(MatchListFragment(), R.id.framelayout)
    }

    override fun onMatchListFragmentInteraction(matchId: String?) {
        replaceFragment(MatchDetailFragment.newInstance(matchId!!),R.id.framelayout) {
            addToBackStack(null)
        }
    }

    override fun onFragmentInteraction(coba: String) {

    }

}
