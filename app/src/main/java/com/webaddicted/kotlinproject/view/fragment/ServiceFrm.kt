package com.webaddicted.kotlinproject.view.fragment

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import com.webaddicted.kotlinproject.R
import com.webaddicted.kotlinproject.databinding.FrmDynamicLayoutBinding
import com.webaddicted.kotlinproject.databinding.FrmReceiverBinding
import com.webaddicted.kotlinproject.databinding.RowDynamicLayoutBinding
import com.webaddicted.kotlinproject.global.common.GlobalUtility
import com.webaddicted.kotlinproject.global.common.visible
import com.webaddicted.kotlinproject.view.base.BaseFragment

class ServiceFrm : BaseFragment() {
    private lateinit var mBinding: FrmReceiverBinding

    companion object {
        val TAG = ServiceFrm::class.java.simpleName
        fun getInstance(bundle: Bundle): ServiceFrm {
            val fragment = ServiceFrm()
            fragment.arguments = bundle
            return ServiceFrm()
        }
    }

    override fun getLayout(): Int {
        return R.layout.frm_receiver
    }

    override fun onViewsInitialized(binding: ViewDataBinding?, view: View) {
        mBinding = binding as FrmReceiverBinding
        init()
        clickListener();
    }

    private fun init() {
        mBinding.toolbar.imgBack.visible()
        mBinding.toolbar.txtToolbarTitle.text = resources.getString(R.string.services_title)
    }


    private fun clickListener() {
        mBinding.toolbar.imgBack.setOnClickListener(this)
        
    }

    override fun onClick(v: View) {
        super.onClick(v)
        when (v.id) {
            R.id.img_back -> activity?.onBackPressed()
        }
    }
}

