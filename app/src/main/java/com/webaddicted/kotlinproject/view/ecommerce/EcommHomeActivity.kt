package com.webaddicted.kotlinproject.view.ecommerce

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.webaddicted.kotlinproject.R
import com.webaddicted.kotlinproject.databinding.ActivityCommonBinding
import com.webaddicted.kotlinproject.databinding.ActivityEcomHomeBinding
import com.webaddicted.kotlinproject.view.base.BaseActivity
import com.webaddicted.kotlinproject.view.fragment.TaskFrm

/**
 * Created by Deepak Sharma on 01/07/19.
 */
class EcommHomeActivity : BaseActivity() {

    private lateinit var mBinding: ActivityEcomHomeBinding

    companion object {
        val TAG: String = EcommHomeActivity::class.java.simpleName
        fun newIntent(activity: Activity) {
            activity.startActivity(Intent(activity, EcommHomeActivity::class.java))
        }
    }

    override fun getLayout(): Int {
        return R.layout.activity_ecom_home
    }

    override fun initUI(binding: ViewDataBinding) {
        mBinding = binding as ActivityEcomHomeBinding
        navigateScreen(EcommHomeFrm.TAG)
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
            EcommHomeFrm.TAG -> frm = EcommHomeFrm.getInstance(Bundle())
        }
        if (frm != null) navigateFragment(R.id.container, frm, false)
    }
}