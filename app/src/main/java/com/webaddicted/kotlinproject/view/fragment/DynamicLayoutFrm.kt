package com.webaddicted.kotlinproject.view.fragment

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import com.android.boxlty.global.common.visible
import com.webaddicted.kotlinproject.R
import com.webaddicted.kotlinproject.databinding.FrmDynamicLayoutBinding
import com.webaddicted.kotlinproject.databinding.RowDynamicLayoutBinding
import com.webaddicted.kotlinproject.global.common.GlobalUtility
import com.webaddicted.kotlinproject.view.base.BaseFragment

class DynamicLayoutFrm : BaseFragment() {
    private lateinit var mBinding: FrmDynamicLayoutBinding

    companion object {
        val TAG = DynamicLayoutFrm::class.java.simpleName
        fun getInstance(bundle: Bundle): DynamicLayoutFrm {
            val fragment = DynamicLayoutFrm()
            fragment.arguments = bundle
            return DynamicLayoutFrm()
        }
    }

    override fun getLayout(): Int {
        return R.layout.frm_dynamic_layout
    }

    override fun onViewsInitialized(binding: ViewDataBinding?, view: View) {
        mBinding = binding as FrmDynamicLayoutBinding
        init()
        clickListener();
    }

    private fun init() {
        mBinding.toolbar.imgBack.visible()
        mBinding.toolbar.txtToolbarTitle.text = resources.getString(R.string.dynamic_title)
        addView()
    }

    private fun addView() {
        for (i in 0..7) {
            val rowBinding = GlobalUtility.getLayoutBinding(
                activity,
                R.layout.row_dynamic_layout
            ) as RowDynamicLayoutBinding
            rowBinding.txtDate.setText("19/07/2017" + " " + "to")
            rowBinding.txtAccountNo.setText("ICICI Bank 12355252255455")
            rowBinding.txtVehType.setText("sales")
            rowBinding.txtVehNo.setText("25")
            rowBinding.txtcredit.setText("25000")
            rowBinding.txtDebit.setText("40000")

            mBinding.linearAddLayout.addView(rowBinding.getRoot())
        }
        mBinding.totalCredit.text = (25000 * 8).toString() + ""
        mBinding.totalDedit.text = (40000 * 8).toString() + ""
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

