package com.example.kotlinproject.view.profile

import android.view.View
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.example.kotlinproject.R
import com.example.kotlinproject.view.base.BaseActivity
import com.example.kotlinproject.databinding.ActivityProfileBinding
import com.example.kotlinproject.global.common.GlobalUtility
import java.io.File

/**
 * Created by Deepak Sharma on 01/07/19.
 */
class ProfileActivity : BaseActivity() {
    private lateinit var mBinding: ActivityProfileBinding
    var isCaptureImg: Boolean=false
    override fun getLayout(): Int {
        return R.layout.activity_profile
    }

    override fun initUI(binding: ViewDataBinding) {
        mBinding = binding as ActivityProfileBinding
        init();
        clickListener();
    }

    private fun init() {
        mBinding?.toolbar?.imgBack?.visibility = View.VISIBLE
        mBinding?.toolbar?.txtToolbarTitle?.text = resources.getString(R.string.my_profile)
    }

    private fun clickListener() {
        mBinding?.btnCaptureImage?.setOnClickListener(this)
        mBinding?.btnPickImage?.setOnClickListener(this)
        mBinding?.toolbar?.imgBack?.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_capture_image ->  {checkStoragePermission()
                isCaptureImg = false}
            R.id.btn_pick_image ->{
                checkStoragePermission()
                isCaptureImg = true
            }
            R.id.img_back -> onBackPressed()
        }
    }

    override fun onPermissionGranted(mCustomPermission: List<String>) {
        super.onPermissionGranted(mCustomPermission)
        if(isCaptureImg) imagePicker.captureImage(this, this)
        else imagePicker.selectImage(this, this)
    }

    override fun imagePath(filePath: List<File>) {
        super.imagePath(filePath)
        GlobalUtility.Companion.showImageUsingGLIDE(filePath.get(0), mBinding?.imgProfile!!, getPlaceHolder(1));
    }

}
