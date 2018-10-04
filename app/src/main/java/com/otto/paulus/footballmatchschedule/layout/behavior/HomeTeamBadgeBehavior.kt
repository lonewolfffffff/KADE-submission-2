package com.otto.paulus.footballmatchschedule.layout.behavior

import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.support.v7.widget.Toolbar
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import kotlinx.android.synthetic.main.activity_detail.*

class HomeTeamBadgeBehavior(context: Context, attrs: AttributeSet) : CoordinatorLayout.Behavior<ImageView>(context, attrs),AnkoLogger {
    override fun layoutDependsOn(parent: CoordinatorLayout, child: ImageView, dependency: View): Boolean {
        return dependency is Toolbar
    }

    override fun onDependentViewChanged(parent: CoordinatorLayout, child: ImageView, dependency: View): Boolean {
        //TODO : ganti ke exponential formula, ini masih linear
        info("Y = "+dependency.y+" * alpha = "+child.alpha)
        child.alpha = dependency.y/250
        if(dependency.y<=10f) {

        }
        return false
    }

}