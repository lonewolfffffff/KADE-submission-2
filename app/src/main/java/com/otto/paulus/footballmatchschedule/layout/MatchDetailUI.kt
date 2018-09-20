package com.otto.paulus.footballmatchschedule.layout

import android.view.View
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.linearLayout
import org.jetbrains.anko.textView
import com.otto.paulus.footballmatchschedule.activity.fragment.MatchDetailFragment

class MatchDetailUI:AnkoComponent<MatchDetailFragment> {
    override fun createView(ui: AnkoContext<MatchDetailFragment>): View = with(ui) {
        linearLayout {
            textView("coba")
        }
    }

}