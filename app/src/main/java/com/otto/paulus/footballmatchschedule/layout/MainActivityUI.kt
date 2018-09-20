package com.otto.paulus.footballmatchschedule.layout

import android.view.View
import android.widget.FrameLayout
import com.otto.paulus.footballmatchschedule.activity.MainActivity
import org.jetbrains.anko.*


class MainActivityUI : AnkoComponent<MainActivity> {

    lateinit var frameLayout: FrameLayout

    override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
        linearLayout {
            frameLayout = frameLayout {
                id = View.generateViewId()
            }
        }
    }
}

