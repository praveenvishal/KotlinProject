package com.webaddicted.kotlinproject.global.behavior

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.appbar.AppBarLayout

/**
 * Created by Deepak Sharma(webaddicted) on 18-11-2019.
 */
internal class ScrollingToolbarBehavior(context: Context, attrs: AttributeSet) :
    CoordinatorLayout.Behavior<Toolbar>(context, attrs) {

    override fun layoutDependsOn(parent: CoordinatorLayout, child: Toolbar, dependency: View): Boolean {
        return dependency is AppBarLayout
    }

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: Toolbar,
        dependency: View
    ): Boolean {
        if (dependency is AppBarLayout) {

            val distanceToScroll = child.height

            val bottomToolbarHeight =
                child.height//TODO replace this with bottom toolbar height.

            val ratio = dependency.y / bottomToolbarHeight.toFloat()
            child.translationY = -distanceToScroll * ratio
        }
        return true
    }
}
