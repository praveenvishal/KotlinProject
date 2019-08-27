package com.example.kotlinproject.view.home

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.example.kotlinproject.R
import com.example.kotlinproject.databinding.ActivityCommonBinding
import com.example.kotlinproject.view.base.BaseActivity
import com.example.kotlinproject.view.news.NewsFragment
import com.example.kotlinproject.view.task.TaskFrm

/**
 * Created by Deepak Sharma on 01/07/19.
 */
class HomeActivity : BaseActivity() {

    private lateinit var mBinding: ActivityCommonBinding

    override fun getLayout(): Int {
        return R.layout.activity_common
    }

    override fun initUI(binding: ViewDataBinding) {
        mBinding = binding as ActivityCommonBinding
        navigateScreen(TaskFrm.TAG)
    }
    /**
     * navigate on fragment
     *
     *
     * @param tag represent navigation activity
     */
    private fun navigateScreen(tag: String) {
        val frm: Fragment
        if (tag == TaskFrm.TAG) {
            frm = TaskFrm.getInstance(Bundle())
            navigateFragment(R.id.container, frm, false)
        }
    }
}