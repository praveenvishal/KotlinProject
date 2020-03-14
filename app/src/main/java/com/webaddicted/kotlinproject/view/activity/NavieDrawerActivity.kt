package com.webaddicted.kotlinproject.view.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.webaddicted.kotlinproject.R
import com.webaddicted.kotlinproject.databinding.ActivityNavDrawerBinding
import com.webaddicted.kotlinproject.global.common.AppApplication.Companion.context
import com.webaddicted.kotlinproject.global.common.visible
import com.webaddicted.kotlinproject.view.base.BaseActivity
import com.webaddicted.kotlinproject.view.fragment.NewsFrm
import kotlinx.android.synthetic.main.nav_header_main.view.*


/**
 * Created by Deepak Sharma on 01/07/19.
 */
class NavieDrawerActivity : BaseActivity() {

    private lateinit var mBinding: ActivityNavDrawerBinding

    companion object {
        val TAG: String = NavieDrawerActivity::class.java.simpleName
        fun newIntent(activity: Activity) {
            activity.startActivity(Intent(activity, NavieDrawerActivity::class.java))
        }
    }

    override fun getLayout(): Int {
        return R.layout.activity_nav_drawer
    }

    override fun initUI(binding: ViewDataBinding) {
        mBinding = binding as ActivityNavDrawerBinding
        init()
        clickListener()
    }

    private fun init() {
        setNavigationColor(ContextCompat.getColor(context!!,R.color.app_color))
        mBinding.toolbar.imgBack?.visible()
        mBinding.toolbar.txtToolbarTitle?.text = resources.getString(R.string.navigation_drawer)
        mBinding.toolbar.imgBack?.setImageResource(R.drawable.nevigaiton)
        navigationDrawer()
        navigateScreen(NewsFrm.TAG)
    }

    private fun navigationDrawer() {
        var navView = mBinding.navView.getHeaderView(0);
        navView.txt_create_lead.setOnClickListener(this)
//        navView.txt_my_lead.setOnClickListener(this)
        navView.txt_logout.setOnClickListener(this)
        navView.txt_home.setOnClickListener(this)
        navView.txt_profile.setOnClickListener(this)
        navView.txt_faq.setOnClickListener(this)
    }

    private fun clickListener() {
        mBinding.toolbar.imgBack.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                openCloseDrawer(true)
            }
        })

    }

    override fun onClick(v: View) {
        super.onClick(v)
        when (v.id) {
            R.id.img_back -> openCloseDrawer(true)
        }
    }
    fun openCloseDrawer(openDrawer: Boolean) {
        if (openDrawer) mBinding.drawerLayout.openDrawer(GravityCompat.START)
        else mBinding.drawerLayout.closeDrawer(GravityCompat.START)
    }

    override fun onBackPressed() {
        if (mBinding.drawerLayout.isDrawerOpen(GravityCompat.START))
            mBinding.drawerLayout.closeDrawer(GravityCompat.START)
        else
            super.onBackPressed()
    }
    /**
     * navigate on fragment
     *
     *
     * @param tag represent navigation activity
     */
    private fun navigateScreen(tag: String) {
        var frm: Fragment? = null
        when (tag) {
            NewsFrm.TAG -> frm = NewsFrm.getInstance(Bundle())
        }
        if (frm != null) {
          navigateFragment(R.id.container, frm!!, false)
        }
    }

}