package com.example.kotlinproject.view.splash

import android.content.Intent
import android.os.Handler
import androidx.databinding.ViewDataBinding
import com.example.kotlinproject.R
import com.example.kotlinproject.view.base.BaseActivity
import com.example.kotlinproject.databinding.ActivitySplashBinding
import com.example.kotlinproject.global.constant.AppConstant
import com.example.kotlinproject.view.news.NewsActivity
import com.example.kotlinproject.view.onBoarding.OnBoardActivity

/**
 * Created by Deepak Sharma on 01/07/19.
 */
class SplashActivity : BaseActivity() {

    private lateinit var mBinding: ActivitySplashBinding

    override fun getLayout(): Int {
        return R.layout.activity_splash
    }

    override fun initUI(binding: ViewDataBinding) {
        mBinding = binding as ActivitySplashBinding
        navigateToNext()
    }
    /**
     * navigate to welcome activity after Splash timer Delay
     */
    private fun navigateToNext() {
        Handler().postDelayed({
            startActivity(Intent(this@SplashActivity, OnBoardActivity::class.java))
            finish()
        }, AppConstant.SPLASH_DELAY)
    }
}