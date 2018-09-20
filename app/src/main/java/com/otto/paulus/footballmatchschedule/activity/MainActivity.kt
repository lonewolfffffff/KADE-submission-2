package com.otto.paulus.footballmatchschedule.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.otto.paulus.footballmatchschedule.activity.fragment.MatchDetailFragment
import com.otto.paulus.footballmatchschedule.activity.fragment.MatchListFragment
import com.otto.paulus.footballmatchschedule.layout.MainActivityUI
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.setContentView

class MainActivity : AppCompatActivity(), MatchListFragment.OnFragmentInteractionListener, MatchDetailFragment.OnFragmentInteractionListener, AnkoLogger {
    private val mainActivity: MainActivityUI = MainActivityUI()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainActivity.setContentView(this)
        supportFragmentManager.beginTransaction().replace(mainActivity.frameLayout.id, MatchListFragment()).commit()
    }

    override fun onMatchListFragmentInteraction(matchId: String?) {
        supportFragmentManager
                .beginTransaction()
                .replace(mainActivity.frameLayout.id, MatchDetailFragment.newInstance(matchId!!))
                .addToBackStack(null)
                .commit()
    }

    override fun onFragmentInteraction(coba: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
