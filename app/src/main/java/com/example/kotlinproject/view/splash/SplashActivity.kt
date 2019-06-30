package com.example.kotlinproject.view.splash

import android.content.Intent
import android.os.Handler
import androidx.databinding.ViewDataBinding
import com.example.kotlinproject.R
import com.example.kotlinproject.base.BaseActivity
import com.example.kotlinproject.databinding.ActivitySplashBinding
import com.example.kotlinproject.global.constant.AppConstant
import com.example.kotlinproject.view.MainActivity

class SplashActivity : BaseActivity() {
    private var mBinding: ActivitySplashBinding? = null

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
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }, AppConstant.SPLASH_DELAY)
    }
}