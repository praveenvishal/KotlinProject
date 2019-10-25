package com.webaddicted.kotlinproject.view.activity

import android.os.Handler
import android.view.View
import androidx.databinding.ViewDataBinding
import com.webaddicted.kotlinproject.R
import com.webaddicted.kotlinproject.databinding.ActivitySplashBinding
import com.webaddicted.kotlinproject.global.common.Lg
import com.webaddicted.kotlinproject.global.constant.AppConstant
import com.webaddicted.kotlinproject.view.base.BaseActivity
import java.util.*



/**
 * Created by Deepak Sharma on 01/07/19.
 */
class SplashActivity : BaseActivity() {
    public val TAG: String = SplashActivity::class.java.simpleName
    private lateinit var mBinding: ActivitySplashBinding
    override fun getLayout(): Int {
        return R.layout.activity_splash
    }

    override fun isNetworkConnected(isInternetConnected: Boolean) {
        showInternetSnackbar(isInternetConnected, mBinding.txtNoInternet)
    }

    override fun initUI(binding: ViewDataBinding) {
        mBinding = binding as ActivitySplashBinding
        init()
        setNavigationColor(resources.getColor(R.color.app_color))
        mBinding.imgLogo.setOnClickListener(object :View.OnClickListener{
            override fun onClick(p0: View?) {
                init()
            }
        })
    }

    var isInternetConnectedtest = true
    private fun init() {
//        mBinding.imgLogo.setOnClickListener(object : View.OnClickListener {
//            override fun onClick(p0: View?) {
//    if (isInternetConnectedtest){
//        isInternetConnectedtest = false
//    }else{
//        isInternetConnectedtest = true
//    }
//                showInternetSnackbar(isInternetConnectedtest, mBinding.txtNoInternet)
//            }
//        })
        navigateToNext()
        val myScanner = Scanner(System.`in`)
        println("Enter the value of N")
        val n = 5

        val a = generateShuffledArrayOfRange(n)
        val arrayWithMissingNum = a.subList(0, n - 1)
        val missingNum = a[n - 1]

        Lg.d(TAG,"Shuffled array = $arrayWithMissingNum")
        Lg.d(TAG,"Missing number = $missingNum")

        val missingNumberFromAlgo = MySolution(arrayWithMissingNum)
        Lg.d(TAG,"Missing number from algo = $missingNumberFromAlgo")

        val isAnswerCorrect = missingNumberFromAlgo == missingNum
        Lg.d(TAG,"Your solution is $isAnswerCorrect")
    }
    fun generateShuffledArrayOfRange(n: Int): List<Int> {
        val a = (1..n).toList()
        val temp = a.toMutableList()
        temp.shuffle()
        return temp.toList()
    }


    fun MySolution(arrayWithMissingNum: List<Int>): Int {
        var sum = 0
        var idx = -1
        for (i in 0 until arrayWithMissingNum.size) {
            if (arrayWithMissingNum[i] === 0) {
                idx = i
            } else {
                sum += arrayWithMissingNum[i]
            }
        }

// the total sum of numbers between 1 and arr.length.
        val total = (arrayWithMissingNum.size + 1) * arrayWithMissingNum.size/ 2

        Lg.d(TAG,"missing number is: " + (total - sum) + " at index " + idx)
        Lg.d(TAG, "")
        return 0
    }

    /**
     * navigate to welcome activity after Splash timer Delay
     */
    private fun navigateToNext() {
        Handler().postDelayed({
            LanguageActivity.newIntent(this)
            finish()
        }, AppConstant.SPLASH_DELAY)
    }
}