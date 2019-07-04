package com.example.kotlinproject.view.profile

import android.view.View
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.example.kotlinproject.R
import com.example.kotlinproject.view.base.BaseActivity
import com.example.kotlinproject.databinding.ActivityProfileBinding
import java.io.File
/**
 * Created by Deepak Sharma on 01/07/19.
 */
class ProfileActivity : BaseActivity() {
    private var mBinding: ActivityProfileBinding? = null

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
        mBinding?.toolbar?.imgProfile?.visibility = View.VISIBLE
        mBinding?.toolbar?.txtToolbarTitle?.text = resources.getString(R.string.my_profile)
    }

    private fun clickListener() {
        mBinding?.btnChooseImage?.setOnClickListener(this)
        mBinding?.toolbar?.imgBack?.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_choose_image -> checkStoragePermission()
            R.id.img_back -> onBackPressed()
        }
    }

    override fun onPermissionGranted(mCustomPermission: List<String>) {
        super.onPermissionGranted(mCustomPermission)
        captureImage()
    }

    override fun imagePath(filePath: List<File>) {
        super.imagePath(filePath)
        Glide.with(this).load(filePath.get(0)).placeholder(R.drawable.logo)
            .into(mBinding?.imgProfile)

    }
}
