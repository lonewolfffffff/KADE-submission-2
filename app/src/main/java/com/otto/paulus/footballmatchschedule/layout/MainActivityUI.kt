package com.otto.paulus.footballmatchschedule.layout

import android.support.design.widget.BottomNavigationView
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Spinner
import com.otto.paulus.footballmatchschedule.R.color.colorAccent
import com.otto.paulus.footballmatchschedule.activity.MainActivity
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import android.support.v7.widget.DividerItemDecoration
import android.widget.FrameLayout
import com.otto.paulus.footballmatchschedule.R
import org.jetbrains.anko.design.bottomNavigationView
import org.jetbrains.anko.design.coordinatorLayout


class MainActivityUI : AnkoComponent<MainActivity> {
    lateinit var listLastEvents: RecyclerView
    //lateinit var listNextEvents: RecyclerView
    lateinit var progressBar: ProgressBar
    lateinit var swipeRefresh: SwipeRefreshLayout
    //lateinit var spinner: Spinner
    lateinit var bottomNavigation: BottomNavigationView
    lateinit var frameLayout: FrameLayout

    override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {

        relativeLayout {
            lparams (width = matchParent, height = matchParent)
            topPadding = dip(16)

            //spinner = spinner ()

            bottomNavigation = bottomNavigationView {
                id = Id.bottomNavigation
                inflateMenu(R.menu.navigation)
            }.lparams{
                width = matchParent
                height = wrapContent
                alignParentBottom()
            }

            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light)
                frameLayout = frameLayout {
                    relativeLayout{
                        lparams (width = matchParent, height = matchParent)

                        listLastEvents = recyclerView {
                            lparams (width = matchParent, height = wrapContent)
                            layoutManager = LinearLayoutManager(ctx)
                            addItemDecoration(DividerItemDecoration(ctx, DividerItemDecoration.VERTICAL))
                        }

                        progressBar = progressBar {
                        }.lparams{
                            centerInParent()
                        }
                    }
                }
            }.lparams {
                above(bottomNavigation)
            }

        }
    }

    companion object Id {
        val bottomNavigation = 1
    }
}

