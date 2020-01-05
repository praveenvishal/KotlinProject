package com.webaddicted.kotlinproject.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.webaddicted.kotlinproject.R
import com.webaddicted.kotlinproject.databinding.FrmCoroutineBinding
import com.webaddicted.kotlinproject.global.common.showToast
import com.webaddicted.kotlinproject.global.common.visible
import com.webaddicted.kotlinproject.view.base.BaseFragment
import kotlinx.coroutines.*
import java.util.*
import java.util.concurrent.TimeUnit

class CoroutineFrm : BaseFragment() {
    private lateinit var mBinding: FrmCoroutineBinding
    private lateinit var job: Job

    companion object {
        val TAG = CoroutineFrm::class.java.simpleName
        fun getInstance(bundle: Bundle): CoroutineFrm {
            val fragment = CoroutineFrm()
            fragment.arguments = bundle
            return CoroutineFrm()
        }
    }

    override fun getLayout(): Int {
        return R.layout.frm_coroutine
    }

    override fun onViewsInitialized(binding: ViewDataBinding?, view: View) {
        mBinding = binding as FrmCoroutineBinding
        init()
        clickListener()
    }

    private fun init() {
        mBinding.toolbar.imgBack.visible()
        mBinding.toolbar.txtToolbarTitle.text = resources.getString(R.string.coroutine_title)
        job = Job()
    }

    private fun clickListener() {
        mBinding.btnLaunch.setOnClickListener(this)
        mBinding.btnSequentially.setOnClickListener(this)
        mBinding.btnParallel.setOnClickListener(this)
        mBinding.btnLaunchTimeout.setOnClickListener(this)
        mBinding.btnExceptionHandler.setOnClickListener(this)
        mBinding.btnLifecycleAware.setOnClickListener(this)
        mBinding.btnAndroidScoped.setOnClickListener(this)
        mBinding.btnCancel.setOnClickListener(this)
        mBinding.toolbar.imgBack.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        super.onClick(v)
        when (v.id) {
            R.id.img_back -> activity?.onBackPressed()
            R.id.btn_launch -> launchCoroutine()
            R.id.btn_sequentially -> launchSequentially()
            R.id.btn_parallel -> launchParallel()
            R.id.btn_launch_timeout -> launchLaunchTimeout()
            R.id.btn_exception_handler -> launchExceptionHandler()
            R.id.btn_lifecycle_aware -> launchLifecycleAware()
            R.id.btn_android_scoped -> launchAndroidScope()
            R.id.btn_cancel -> {
                job.cancel()
                activity?.showToast(getString(R.string.job_cancel))
                mBinding.txtCancel.setText(getString(R.string.job_cancel))
            }
        }
    }

    private fun launchCoroutine() {
        mBinding.txtLaunch.setText("Step 1 ")
        GlobalScope.launch(Dispatchers.Main + job) {
            mBinding.txtLaunch.setText(mBinding.txtLaunch.text.toString() + "\nStep 2")
            var result = loadData(mBinding.txtLaunch)
            Log.d(TAG, "Step 5 :- result - $result")
            mBinding.txtLaunch.setText(mBinding.txtLaunch.text.toString() + "\nStep 5 :- result - $result")
        }
    }


    private fun launchSequentially() {
        mBinding.txtSequentially.setText("Step 1 ")
        GlobalScope.launch(Dispatchers.Main + job) {
            mBinding.txtSequentially.setText(mBinding.txtSequentially.text.toString() + "\nStep 2")
            var result1 = loadData(mBinding.txtSequentially)
            var result2 = loadData(mBinding.txtSequentially)
            mBinding.txtSequentially.setText(mBinding.txtSequentially.text.toString() + "\nStep 5 :- result1 - $result1 \n result2 - $result2")
        }
    }

    private fun launchParallel() {
        mBinding.txtParallel.setText("Step 1 ")
        GlobalScope.launch(Dispatchers.Main + job) {
            mBinding.txtParallel.setText(mBinding.txtParallel.text.toString() + "\nStep 2")
            var result1 = async { loadData(mBinding.txtParallel) }.await()
            var result2 = async { loadData(mBinding.txtParallel) }.await()
            mBinding.txtParallel.setText(mBinding.txtParallel.text.toString() + "\nStep 5 :- result1 - $result1 \n result2 - $result2")
        }
    }

    private fun launchLaunchTimeout() {
        mBinding.txtLaunchTimeout.setText("Step 1 ")
        GlobalScope.launch(Dispatchers.Main + job) {
            mBinding.txtLaunchTimeout.setText(mBinding.txtLaunchTimeout.text.toString() + "\nStep 2")
            var result = withTimeoutOrNull(TimeUnit.SECONDS.toMillis(1)) { loadData(mBinding.txtLaunchTimeout) }
            mBinding.txtLaunchTimeout.setText(mBinding.txtLaunchTimeout.text.toString() + "\nStep 5 :- time out :- $result")
        }
    }

    private fun launchExceptionHandler() {
        mBinding.txtExceptionHandler.setText("Step 1 ")
        GlobalScope.launch(Dispatchers.Main + job) {
            mBinding.txtExceptionHandler.setText(mBinding.txtExceptionHandler.text.toString() + "\nStep 2")
            try {
                val result = loadData(mBinding.txtLaunch)
                mBinding.txtExceptionHandler.setText(mBinding.txtExceptionHandler.text.toString() + "\nStep 5 :- try :- $result")

            } catch (e: IllegalArgumentException) {
                mBinding.txtExceptionHandler.setText(mBinding.txtExceptionHandler.text.toString() + "\nStep 5 :- catch :- ${e.message}")
            }
            mBinding.txtExceptionHandler.setText(mBinding.txtExceptionHandler.text.toString() + "\nStep 6 :- out function")
        }
    }

    private fun launchLifecycleAware() {
        navigateScreen(CoroutineLifecycleAwareFrm.TAG)
    }

    private fun launchAndroidScope() {
        navigateScreen(CoroutineScopeFrm.TAG)
    }

    suspend fun loadData(txtLaunch: TextView): String {
        txtLaunch.setText(txtLaunch.text.toString() + "\nStep 3")
        delay(TimeUnit.SECONDS.toMillis(3)) // imitate long running operation
        txtLaunch.setText(txtLaunch.text.toString() + "\nStep 4")
        return "Data is available: ${Random().nextInt()}"
    }


    fun navigateScreen(tag: String) {
        var frm: Fragment? = null
        when (tag) {
            CoroutineLifecycleAwareFrm.TAG -> frm = CoroutineLifecycleAwareFrm.getInstance(Bundle())
            CoroutineScopeFrm.TAG -> frm = CoroutineScopeFrm.getInstance(Bundle())
        }
        frm?.let { navigateAddFragment(R.id.container, it, true) }
    }
}

