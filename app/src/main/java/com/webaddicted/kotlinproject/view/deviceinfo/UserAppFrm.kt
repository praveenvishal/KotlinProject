package com.webaddicted.kotlinproject.view.deviceinfo

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import com.webaddicted.kotlinproject.R
import com.webaddicted.kotlinproject.databinding.FrmDevUserAppBinding
import com.webaddicted.kotlinproject.databinding.FrmDeviceInfoBinding
import com.webaddicted.kotlinproject.global.common.visible
import com.webaddicted.kotlinproject.view.base.BaseFragment

class UserAppFrm : BaseFragment() {
    private lateinit var mBinding: FrmDevUserAppBinding

    companion object {
        val TAG = UserAppFrm::class.java.simpleName
        fun getInstance(bundle: Bundle): UserAppFrm {
            val fragment =
                UserAppFrm()
            fragment.arguments = bundle
            return UserAppFrm()
        }
    }

    override fun getLayout(): Int {
        return R.layout.frm_dev_user_app
    }

    override fun onViewsInitialized(binding: ViewDataBinding?, view: View) {
        mBinding = binding as FrmDevUserAppBinding
        init()
        clickListener();
    }

    private fun init() {
//        mBinding.toolbar.imgBack.visible()
//        mBinding.toolbar.txtToolbarTitle.text = resources.getString(R.string.device_user_app_title)

    }

    private fun clickListener() {
//        mBinding.toolbar.imgBack.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        super.onClick(v)
        when (v.id) {
            R.id.img_back -> activity?.onBackPressed()
        }
    }
}
