package com.example.kotlinproject.base

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProviders
import com.example.kotlinproject.R
import com.example.kotlinproject.global.common.AppApplication
import com.example.kotlinproject.global.common.GlobalUtility
import com.example.kotlinproject.global.common.ImagePicker
import com.example.kotlinproject.global.common.PermissionHelper
import com.example.kotlinproject.global.sharedPref.PreferenceMgr
import com.example.kotlinproject.model.eventBus.EventBusListener
import com.example.kotlinproject.viewModel.home.HomeViewModel
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.koin.android.ext.android.inject
import java.io.File

abstract class BaseActivity : AppCompatActivity(), View.OnClickListener, PermissionHelper.Companion.PermissionListener,
    ImagePicker.ImagePickerListener {

    private val imagePicker: ImagePicker  by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out)
        supportActionBar?.hide()
        GlobalUtility.hideKeyboard(this)
        AppApplication.mCurrencyActivity = this
        var layoutResId = getLayout()
        var binding: ViewDataBinding? = null
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

    override fun onClick(v: View?) {
    }
    //    override fun onClick(v: View?) {
//        super.onClick(v)
//    }
//    open fun onClickListener(v: View?){
//
//    }
    protected fun checkStoragePermission() {
        val multiplePermission = ArrayList<String>()
        multiplePermission.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        multiplePermission.add(Manifest.permission.READ_EXTERNAL_STORAGE)
        multiplePermission.add(Manifest.permission.CAMERA)
        if (PermissionHelper.checkMultiplePermission(this, multiplePermission)) {
//            FileUtils.createApplicationFolder()
//            permissionGranted(multiplePermission);
            onPermissionGranted(multiplePermission)
        } else
            PermissionHelper.requestMultiplePermission(this, multiplePermission, this)
    }


    protected fun checkLocationPermission() {
        val multiplePermission = ArrayList<String>()
        multiplePermission.add(Manifest.permission.ACCESS_FINE_LOCATION)
        multiplePermission.add(Manifest.permission.ACCESS_COARSE_LOCATION)
        if (PermissionHelper.checkMultiplePermission(this, multiplePermission)) {
        } else
            PermissionHelper.requestMultiplePermission(this, multiplePermission, this)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        PermissionHelper.Companion.onRequestPermissionsResult(this, requestCode, permissions, grantResults)
    }


    override fun onPermissionGranted(mCustomPermission: List<String>) {
    }

    override fun onPermissionDenied(mCustomPermission: List<String>) {
    }

    protected fun captureImage() {
        imagePicker.captureImage(this, this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("TAG", "my tag")
        if (resultCode == RESULT_OK) {
            Log.d("TAG", "my taggggg ")
            if (requestCode == ImagePicker.REQUEST_CAMERA || requestCode == ImagePicker.SELECT_FILE || requestCode == ImagePicker.PICK_IMAGE_MULTIPLE || requestCode == ImagePicker.REQUEST_CAPTURE_IMAGE) {
                imagePicker.onActivityResult(this, requestCode, resultCode, data)
            }
        }
    }

    override fun imagePath(filePath: List<File>) {

    }
}