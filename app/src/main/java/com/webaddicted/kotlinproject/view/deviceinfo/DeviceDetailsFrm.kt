package com.webaddicted.kotlinproject.view.deviceinfo

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.viewpager.widget.ViewPager
import com.webaddicted.kotlinproject.R
import com.webaddicted.kotlinproject.databinding.FrmDevBasicBinding
import com.webaddicted.kotlinproject.databinding.FrmDeviceInfoBinding
import com.webaddicted.kotlinproject.global.common.visible
import com.webaddicted.kotlinproject.global.misc.ViewPagerAdapter
import com.webaddicted.kotlinproject.view.base.BaseFragment

class DeviceDetailsFrm : BaseFragment() {
    private lateinit var mBinding: FrmDevBasicBinding

    companion object {
        val TAG = DeviceDetailsFrm::class.java.simpleName
        fun getInstance(bundle: Bundle): DeviceDetailsFrm {
            val fragment =
                DeviceDetailsFrm()
            fragment.arguments = bundle
            return DeviceDetailsFrm()
        }
    }

    override fun getLayout(): Int {
        return R.layout.frm_dev_basic
    }

    override fun onViewsInitialized(binding: ViewDataBinding?, view: View) {
        mBinding = binding as FrmDevBasicBinding
        init()
        clickListener();
    }

    private fun init() {
//        mBinding.toolbar.imgBack.visible()
//        mBinding.toolbar.txtToolbarTitle.text = resources.getString(R.string.dialog_title)

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
