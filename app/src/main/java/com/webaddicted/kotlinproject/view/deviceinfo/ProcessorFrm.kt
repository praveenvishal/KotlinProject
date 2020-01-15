package com.webaddicted.kotlinproject.view.deviceinfo

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.viewpager.widget.ViewPager
import com.webaddicted.kotlinproject.R
import com.webaddicted.kotlinproject.databinding.FrmDevProcessorBinding
import com.webaddicted.kotlinproject.databinding.FrmDeviceInfoBinding
import com.webaddicted.kotlinproject.global.common.visible
import com.webaddicted.kotlinproject.global.misc.ViewPagerAdapter
import com.webaddicted.kotlinproject.view.base.BaseFragment

class ProcessorFrm : BaseFragment() {
    private lateinit var mBinding: FrmDevProcessorBinding

    companion object {
        val TAG = ProcessorFrm::class.java.simpleName
        fun getInstance(bundle: Bundle): ProcessorFrm {
            val fragment =
                ProcessorFrm()
            fragment.arguments = bundle
            return ProcessorFrm()
        }
    }

    override fun getLayout(): Int {
        return R.layout.frm_dev_processor
    }

    override fun onViewsInitialized(binding: ViewDataBinding?, view: View) {
        mBinding = binding as FrmDevProcessorBinding
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
