package com.example.kotlinproject.view.profile

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import com.example.kotlinproject.R
import com.example.kotlinproject.databinding.ActivityProfileBinding
import com.example.kotlinproject.global.common.GlobalUtility
import com.example.kotlinproject.view.base.BaseFragment
import java.io.File

class ProfileFragment : BaseFragment() {
    private    var isCaptureImg: Boolean=false
    private lateinit var mBinding: ActivityProfileBinding
    companion object {
        val TAG = ProfileFragment::class.java.simpleName
        fun getInstance(bundle: Bundle): ProfileFragment {
            val fragment = ProfileFragment()
            fragment.setArguments(bundle)
            return ProfileFragment()
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
        mBinding?.toolbar?.imgBack?.visibility = View.VISIBLE
        mBinding?.toolbar?.txtToolbarTitle?.text = resources.getString(R.string.my_profile)
    }

    private fun clickListener() {
        mBinding?.btnCaptureImage?.setOnClickListener(this)
        mBinding?.btnPickImage?.setOnClickListener(this)
        mBinding?.toolbar?.imgBack?.setOnClickListener(this)
    }


    override fun onClick(v: View) {
        super.onClick(v)
        when (v?.id) {
            R.id.btn_capture_image ->  {isCaptureImg = true
                checkStoragePermission()
            }
            R.id.btn_pick_image ->{
                isCaptureImg = false
                checkStoragePermission()
            }
            R.id.img_back -> activity?.onBackPressed()
        }
    }

    override fun onPermissionGranted(mCustomPermission: List<String>) {
        super.onPermissionGranted(mCustomPermission)
        if(isCaptureImg) imagePicker.captureImage(activity!!, this)
        else imagePicker.selectImage(activity!!, this)
    }

    override fun imagePath(filePath: List<File>) {
        super.imagePath(filePath)
        GlobalUtility.Companion.showImageUsingGLIDE(filePath.get(0), mBinding?.imgProfile, getPlaceHolder(1));
    }
}

