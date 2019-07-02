package com.example.kotlinproject.base

import android.Manifest
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProviders
import com.example.kotlinproject.R
import com.example.kotlinproject.global.common.AppApplication
import com.example.kotlinproject.global.common.GlobalUtility
import com.example.kotlinproject.global.common.PermissionHelper
import com.example.kotlinproject.model.eventBus.EventBusListener
import com.example.kotlinproject.viewModel.HomeViewModel
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

open class BaseActivity : AppCompatActivity(), PermissionHelper.Companion.PermissionListener {
    private var mHomeViewModel: HomeViewModel? = null
    //    var mHomeViewModel: HomeViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out)
        supportActionBar?.hide()
        GlobalUtility.hideKeyboard(this)
        AppApplication.mCurrencyActivity = this
        var layoutResId = getLayout()
        var binding: ViewDataBinding? = null
        mHomeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        if (layoutResId != 0) {
            try {
                binding = DataBindingUtil.setContentView(this, layoutResId)
                initUI(binding)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    open fun getLayout(): Int {
        return 0
    }

    open fun initUI(binding: ViewDataBinding) {
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this)
    }

    @Subscribe
    fun EventBusListener(eventBusListener: EventBusListener) {
    }

    protected fun checkStoragePermission() {
        val multiplePermission = ArrayList<String>()
        multiplePermission.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        multiplePermission.add(Manifest.permission.READ_EXTERNAL_STORAGE)
        multiplePermission.add(Manifest.permission.CAMERA)
        if (PermissionHelper.checkMultiplePermission(this,multiplePermission)) {
//            FileUtils.createApplicationFolder()
            mHomeViewModel?.mIsPermissionGranted?.postValue(true)
        } else
            PermissionHelper.requestMultiplePermission(this,multiplePermission, this)
    }

    protected fun checkLocationPermission() {
        val multiplePermission = ArrayList<String>()
        multiplePermission.add(Manifest.permission.ACCESS_FINE_LOCATION)
        multiplePermission.add(Manifest.permission.ACCESS_COARSE_LOCATION)
        if (PermissionHelper.checkMultiplePermission(this,multiplePermission)) {
        } else
            PermissionHelper.requestMultiplePermission(this,multiplePermission, this)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        PermissionHelper.Companion.onRequestPermissionsResult(this, requestCode, permissions, grantResults)
    }

    override fun onPermissionGranted(mCustomPermission: List<String>) {
        mHomeViewModel?.mIsPermissionGranted?.postValue(true)
    }

    override fun onPermissionDenied(mCustomPermission: List<String>) {
        mHomeViewModel?.mIsPermissionGranted?.postValue(false)
    }

}