package com.webaddicted.kotlinproject.view.fragment

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import com.webaddicted.kotlinproject.R
import com.webaddicted.kotlinproject.databinding.ActivityProfileBinding
import com.webaddicted.kotlinproject.global.common.GlobalUtility
import com.webaddicted.kotlinproject.view.base.BaseFragment
import java.io.File

class ProfileFrm : BaseFragment() {
    private var isCaptureImg: Boolean = false
    private lateinit var mBinding: ActivityProfileBinding

    companion object {
        val TAG = ProfileFrm::class.java.simpleName
        fun getInstance(bundle: Bundle): ProfileFrm {
            val fragment = ProfileFrm()
            fragment.setArguments(bundle)
            return ProfileFrm()
        }
    }

    override fun getLayout(): Int {
        return R.layout.activity_profile
    }

    override fun onViewsInitialized(binding: ViewDataBinding?, view: View) {
        mBinding = binding as ActivityProfileBinding
        init()
        clickListener();
    }

    private fun init() {
        mBinding.toolbar.imgBack.visibility = View.VISIBLE
        mBinding.toolbar.txtToolbarTitle.text = resources.getString(R.string.my_profile)
    }

    private fun clickListener() {
        mBinding.btnCaptureImage.setOnClickListener(this)
        mBinding.btnPickImage.setOnClickListener(this)
        mBinding.toolbar.imgBack.setOnClickListener(this)
    }


    override fun onClick(v: View) {
        super.onClick(v)
        when (v.id) {
            R.id.btn_capture_image -> {
                isCaptureImg = true
                checkStoragePermission()
            }
            R.id.btn_pick_image -> {
                isCaptureImg = false
                checkStoragePermission()
            }
            R.id.img_back -> activity?.onBackPressed()
        }
    }

    override fun onPermissionGranted(mCustomPermission: List<String>) {
        super.onPermissionGranted(mCustomPermission)
        if (isCaptureImg) imagePicker.captureImage(activity!!, this)
        else imagePicker.selectImage(activity!!, this)
    }

    override fun imagePath(filePath: List<File>) {
        super.imagePath(filePath)
        GlobalUtility.Companion.showImageUsingGLIDE(
            filePath.get(0),
            mBinding?.imgProfile,
            getPlaceHolder(1)
        );
    }
}

