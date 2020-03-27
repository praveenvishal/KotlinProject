package com.demo.kotlinproject.view.home

import android.app.Activity
import android.content.Intent
import android.os.Handler
import androidx.core.content.ContextCompat
import androidx.databinding.ViewDataBinding
import com.demo.kotlinproject.R
import com.demo.kotlinproject.databinding.ActivitySplashBinding
import com.demo.kotlinproject.global.common.AppApplication.Companion.context
import com.demo.kotlinproject.global.constant.AppConstant
import com.demo.kotlinproject.view.base.BaseActivity

class SplashActivity : BaseActivity() {
    private lateinit var mBinding: ActivitySplashBinding

    companion object {
        val TAG: String = SplashActivity::class.java.simpleName
        fun newIntent(activity: Activity) {
            activity.startActivity(Intent(activity, SplashActivity::class.java))
        }
    }

    override fun getLayout(): Int {
        return R.layout.activity_splash
    }

    override fun initUI(binding: ViewDataBinding) {
        mBinding = binding as ActivitySplashBinding
        init()
        setNavigationColor(ContextCompat.getColor(context!!, R.color.app_color))
        mBinding.imgLogo.setOnClickListener { init() }
    }

    private fun init() {
        navigateToNext()

    }


    /**
     * navigate to welcome activity after Splash timer Delay
     */
    private fun navigateToNext() {
        Handler().postDelayed({
            HomeActivity.newIntent(this)
            finish()
        }, AppConstant.SPLASH_DELAY)
    }
}