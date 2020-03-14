package com.webaddicted.kotlinproject.view.fragment

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import com.google.zxing.integration.android.IntentIntegrator
import com.webaddicted.kotlinproject.R
import com.webaddicted.kotlinproject.databinding.FrmBottomSheetBinding
import com.webaddicted.kotlinproject.global.common.visible
import com.webaddicted.kotlinproject.view.base.BaseFragment


class BottomSheetFrm : BaseFragment() {
    private lateinit var mBinding: FrmBottomSheetBinding

    companion object {
        val TAG = BottomSheetFrm::class.java.simpleName
        fun getInstance(bundle: Bundle): BottomSheetFrm {
            val fragment = BottomSheetFrm()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun getLayout(): Int {
        return R.layout.frm_bottom_sheet
    }

    override fun initUI(binding: ViewDataBinding?, view: View) {
        mBinding = binding as FrmBottomSheetBinding
        init()
        clickListener()
    }

    private fun init() {
        mBinding.toolbar.imgBack.visible()
        mBinding.toolbar.txtToolbarTitle.text = resources.getString(R.string.bottom_sheet)
    }

    private fun clickListener() {
        mBinding.toolbar.imgBack.setOnClickListener(this)
        mBinding.linear.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        super.onClick(v)
        when (v.id) {
            R.id.img_back -> activity?.onBackPressed()
        }
    }

}

