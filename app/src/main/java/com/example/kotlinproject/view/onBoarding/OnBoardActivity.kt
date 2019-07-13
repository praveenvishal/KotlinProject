package com.example.kotlinproject.view.onBoarding

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.WindowManager
import androidx.databinding.ViewDataBinding
import androidx.viewpager.widget.ViewPager
import com.example.kotlinproject.R
import com.example.kotlinproject.databinding.ActivityOnboardingBinding
import com.example.kotlinproject.view.adapter.OnBordingViewPagerAdapter
import com.example.kotlinproject.view.base.BaseActivity
import com.example.kotlinproject.view.news.NewsActivity

/**
 * Created by Deepak Sharma on 01/07/19.
 */
class OnBoardActivity : BaseActivity() {

    private lateinit var mBinding: ActivityOnboardingBinding
    private val layouts = intArrayOf(R.layout.welcome_slide1, R.layout.welcome_slide2, R.layout.welcome_slide3, R.layout.welcome_slide4)

    override fun getLayout(): Int {
        return R.layout.activity_onboarding
    }

    override fun initUI(binding: ViewDataBinding) {
        mBinding = binding as ActivityOnboardingBinding
        init()
        clickListener()
        setAdapter()
    }

    private fun init() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            if (window != null) {
                window.decorView.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.statusBarColor = Color.TRANSPARENT
            }
        }
        mBinding.viewPager.addOnPageChangeListener(getChangeListener())
    }

    private fun clickListener() {
        mBinding.btnGotIt.setOnClickListener(this)
        mBinding.btnNext.setOnClickListener(this)
        mBinding.btnSkip.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        super.onClick(v)
        when (v?.id) {
            R.id.btn_skip, R.id.btn_got_it -> {
                startActivity(Intent(this@OnBoardActivity, NewsActivity::class.java))
                finish()
            }
            R.id.btn_next -> showNextSlide()
        }
    }

    private fun setAdapter() {
        mBinding.viewPager.adapter = OnBordingViewPagerAdapter(this, layouts)
        mBinding.dotsIndicator.setViewPager(mBinding.viewPager)
    }

    private fun getChangeListener(): ViewPager.OnPageChangeListener {
        return object : ViewPager.OnPageChangeListener {
            override fun onPageSelected(position: Int) {
                val isLastPage = position == layouts.size - 1
                mBinding.btnNext.visibility = if (isLastPage) View.GONE else View.VISIBLE
                mBinding.btnSkip.visibility = if (isLastPage) View.INVISIBLE else View.VISIBLE
                mBinding.btnGotIt.visibility = if (isLastPage) View.VISIBLE else View.GONE
            }

            override fun onPageScrolled(arg: Int, arg1: Float, arg2: Int) {}

            override fun onPageScrollStateChanged(arg: Int) {}
        }
    }

    private fun showNextSlide() {
        val nextIndex = mBinding.viewPager.currentItem + 1
        if (mBinding.viewPager != null && nextIndex < layouts.size)
            mBinding.viewPager.currentItem = nextIndex
    }
}