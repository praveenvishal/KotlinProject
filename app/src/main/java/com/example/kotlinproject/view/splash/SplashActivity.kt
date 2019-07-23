package com.example.kotlinproject.view.splash

import android.content.Intent
import android.os.Handler
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProviders
import com.example.kotlinproject.R
import com.example.kotlinproject.databinding.ActivityNewsBinding
import com.example.kotlinproject.view.base.BaseActivity
import com.example.kotlinproject.databinding.ActivitySplashBinding
import com.example.kotlinproject.global.common.GlobalUtility
import com.example.kotlinproject.global.common.ThemeColors
import com.example.kotlinproject.global.constant.AppConstant
import com.example.kotlinproject.view.onBoarding.OnBoardActivity
import com.example.kotlinproject.viewModel.home.HomeViewModel
import com.example.kotlinproject.viewModel.main.MainViewModel
import com.prodege.sbshop.model.repo.AppViewModelFactory
import org.koin.android.ext.android.inject

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
//        var mMainViewModel = ViewModelProviders.of(this, appViewModelFactory).get(MainViewModel::class.java)
//        var mHomeViewModel = ViewModelProviders.of(this, appViewModelFactory).get(HomeViewModel::class.java)
//        GlobalUtility.showToast("splash  "+mMainViewModel.name)
//        mMainViewModel.name = "my name"
//        GlobalUtility.showToast("splash  "+mMainViewModel.name)
//        ThemeColors.Companion.setNewThemeColor(this, 245, 248, 200, preferenceMgr);
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