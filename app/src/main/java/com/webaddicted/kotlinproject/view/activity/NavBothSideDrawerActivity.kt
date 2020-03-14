package com.webaddicted.kotlinproject.view.activity

import android.app.Activity
import android.content.Intent
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.databinding.ViewDataBinding
import com.webaddicted.kotlinproject.R
import com.webaddicted.kotlinproject.databinding.ActivityNavBothDrawerBinding
import com.webaddicted.kotlinproject.global.common.AppApplication.Companion.context
import com.webaddicted.kotlinproject.view.base.BaseActivity


/**
 * Created by Deepak Sharma on 01/07/19.
 */
class NavBothSideDrawerActivity : BaseActivity() {

    private var isLeftDrawer: Boolean = false
    lateinit var animRotate: Animation
    private lateinit var mBinding: ActivityNavBothDrawerBinding

    companion object {
        val TAG: String = NavBothSideDrawerActivity::class.java.simpleName
        fun newIntent(activity: Activity) {
            activity.startActivity(Intent(activity, NavBothSideDrawerActivity::class.java))
        }
    }

    override fun getLayout(): Int {
        return R.layout.activity_nav_both_drawer
    }

    override fun initUI(binding: ViewDataBinding) {
        mBinding = binding as ActivityNavBothDrawerBinding
        init()
        clickListener()
    }

    private fun init() {
        setNavigationColor(ContextCompat.getColor(context!!,R.color.app_color))
        mBinding.toolbar.imgProfile?.visibility = View.VISIBLE
        mBinding.toolbar.imgBack?.visibility = View.VISIBLE
        mBinding.toolbar.txtToolbarTitle?.text = resources.getString(R.string.navigation_drawer)
        mBinding.toolbar.imgProfile?.setImageResource(R.drawable.nevigaiton)
        mBinding.toolbar.imgBack?.setImageResource(R.drawable.nevigaiton)
        animRotate = AnimationUtils.loadAnimation(this, R.anim.rotate)
    }

    private fun clickListener() {
        mBinding.toolbar.imgProfile.setOnClickListener(object : View.OnClickListener{
            override fun onClick(view: View?) {
                isLeftDrawer = false
                openCloseDrawer(true)
            }
        })
        mBinding.toolbar.imgBack.setOnClickListener(object : View.OnClickListener{
            override fun onClick(view: View?) {
                isLeftDrawer = true
                openCloseDrawer(true)
            }
        })
        animRotate.setAnimationListener(object :
            Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation) {}
            override fun onAnimationStart(animation: Animation) {}
            override fun onAnimationEnd(animation: Animation) {
//                if (isLeftDrawer) mBinding.drawerLeftLayout.openDrawer(GravityCompat.START)
//                else mBinding.drawerRightLayout.openDrawer(GravityCompat.END)
            }
        })
    }

    override fun onClick(v: View) {
        super.onClick(v)
        when (v.id) {
            R.id.img_back -> {
                isLeftDrawer = true
                openCloseDrawer(true)
            }
            R.id.img_profile -> {
                isLeftDrawer = false
                openCloseDrawer(true)
            }
        }
    }

    fun openCloseDrawer(openDrawer: Boolean) {
//        if (openDrawer) {
//            if (isLeftDrawer) mBinding.drawerLeftLayout.startAnimation(animRotate)
//            else mBinding.drawerRightLayout.startAnimation(animRotate)
//        } else {
//            if (isLeftDrawer) mBinding.drawerLeftLayout.closeDrawer(GravityCompat.START)
//            else mBinding.drawerRightLayout.closeDrawer(GravityCompat.END)
//        }
    }

    override fun onBackPressed() {
        if (mBinding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            mBinding.drawerLayout.closeDrawer(GravityCompat.START);
        } else if (mBinding.drawerLayout.isDrawerOpen(GravityCompat.END)) {  /*Closes the Appropriate Drawer*/
            mBinding.drawerLayout.closeDrawer(GravityCompat.END);
        } else {
            super.onBackPressed();
            System.exit(0);
        }
    }

}