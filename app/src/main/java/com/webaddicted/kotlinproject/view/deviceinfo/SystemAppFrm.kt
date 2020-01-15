package com.webaddicted.kotlinproject.view.deviceinfo

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import com.webaddicted.kotlinproject.R
import com.webaddicted.kotlinproject.databinding.FrmDevSystemAppBinding
import com.webaddicted.kotlinproject.databinding.FrmDeviceInfoBinding
import com.webaddicted.kotlinproject.global.common.visible
import com.webaddicted.kotlinproject.view.base.BaseFragment

class SystemAppFrm : BaseFragment() {
    private lateinit var mBinding: FrmDevSystemAppBinding

    companion object {
        val TAG = SystemAppFrm::class.java.simpleName
        fun getInstance(bundle: Bundle): SystemAppFrm {
            val fragment =
                SystemAppFrm()
            fragment.arguments = bundle
            return SystemAppFrm()
        }
    }

    override fun getLayout(): Int {
        return R.layout.frm_dev_system_app
    }

    override fun onViewsInitialized(binding: ViewDataBinding?, view: View) {
        mBinding = binding as FrmDevSystemAppBinding
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
