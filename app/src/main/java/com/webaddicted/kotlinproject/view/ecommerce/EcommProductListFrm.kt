package com.webaddicted.kotlinproject.view.ecommerce

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.webaddicted.kotlinproject.R
import com.webaddicted.kotlinproject.databinding.FrmEcomProductBinding
import com.webaddicted.kotlinproject.view.adapter.CategoryPagerAdapterProductGrid
import com.webaddicted.kotlinproject.view.base.BaseFragment

class EcommProductListFrm : BaseFragment() {
    private lateinit var mBinding: FrmEcomProductBinding

    companion object {
        val TAG = EcommProductListFrm::class.java.simpleName
        fun getInstance(bundle: Bundle): EcommProductListFrm {
            val fragment = EcommProductListFrm()
            fragment.arguments = bundle
            return EcommProductListFrm()
        }
    }

    override fun getLayout(): Int {
        return R.layout.frm_ecom_product
    }

    override fun onViewsInitialized(binding: ViewDataBinding?, view: View) {
        mBinding = binding as FrmEcomProductBinding
        init()
        clickListener();
    }

    private fun init() {
        mBinding.tabLayout.setTabMode(TabLayout.MODE_FIXED)
        mBinding.tabLayout.addTab(mBinding.tabLayout.newTab().setText("Popular"))
        mBinding.tabLayout.addTab(mBinding.tabLayout.newTab().setText("Low Price"))
        mBinding.tabLayout.addTab(mBinding.tabLayout.newTab().setText("Hight Price"))
        mBinding.tabLayout.addTab(mBinding.tabLayout.newTab().setText("Assured"))
        mBinding.tabLayout.setTabGravity(TabLayout.GRAVITY_FILL)
        val adapter = CategoryPagerAdapterProductGrid(activity?.supportFragmentManager!!, 4)
        mBinding.pager.setAdapter(adapter)
        mBinding.pager.setOffscreenPageLimit(1)
        mBinding.pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(mBinding.tabLayout))
        mBinding.tabLayout.setOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                mBinding.pager.setCurrentItem(tab.getPosition())
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}

            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

    }

    private fun clickListener() {
        mBinding.imgBack.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        super.onClick(v)
        when (v.id) {
            R.id.img_back -> activity?.onBackPressed()
        }
    }

//    override fun onResume() {
//        super.onResume()
//        addBlankSpace(mBinding.bottomSpace)
//    }
    /**
     * navigate on fragment
     *
     * @param tag represent navigation activity
     */
    private fun navigateScreen(tag: String) {
        var frm: Fragment? = null
        when (tag) {
            EcommOtpFrm.TAG -> frm = EcommOtpFrm.getInstance(Bundle())
        }
        if (frm != null) navigateAddFragment(R.id.container, frm, true)
    }

}

