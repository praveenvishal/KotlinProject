package com.demo.kotlinproject.view.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.demo.kotlinproject.R
import com.demo.kotlinproject.global.common.GlideApp

class BannerPagerAdapter(val imageList: ArrayList<String>?) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val item = LayoutInflater.from(container.context).inflate(
            R.layout.banner_layout, container,
            false
        )
        container.addView(item)
        GlideApp.with(container.context).load(imageList!![position]).into(item.findViewById(R.id.imageViewBanner))
        return item
    }

    override fun getCount(): Int {
        return imageList!!.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}