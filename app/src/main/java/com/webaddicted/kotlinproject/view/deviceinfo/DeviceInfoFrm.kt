package com.webaddicted.kotlinproject.view.deviceinfo

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.viewpager.widget.ViewPager
import com.webaddicted.kotlinproject.R
import com.webaddicted.kotlinproject.databinding.FrmDeviceInfoBinding
import com.webaddicted.kotlinproject.global.common.visible
import com.webaddicted.kotlinproject.global.constant.AppConstant.Companion.IS_USER_COME_FROM_SYSTEM_APPS
import com.webaddicted.kotlinproject.global.constant.AppConstant.Companion.IS_USER_COME_FROM_USER_APPS
import com.webaddicted.kotlinproject.global.misc.ViewPagerAdapter
import com.webaddicted.kotlinproject.view.base.BaseFragment

class DeviceInfoFrm : BaseFragment() {
    private lateinit var mBinding: FrmDeviceInfoBinding

    companion object {
        val TAG = DeviceInfoFrm::class.java.simpleName
        fun getInstance(bundle: Bundle): DeviceInfoFrm {
            val fragment =
                DeviceInfoFrm()
            fragment.arguments = bundle
            return DeviceInfoFrm()
        }
    }

    override fun getLayout(): Int {
        return R.layout.frm_device_info
    }

    override fun onViewsInitialized(binding: ViewDataBinding?, view: View) {
        mBinding = binding as FrmDeviceInfoBinding
        init()
        clickListener()
    }

    private fun init() {
        mBinding.toolbar.imgBack.visible()
        mBinding.toolbar.txtToolbarTitle.text = resources.getString(R.string.device_info_title)
        mBinding.tabLayout.setupWithViewPager(mBinding.viewPager)
//        mBinding.viewPager.offscreenPageLimit=20
        setupViewPager(mBinding.viewPager)
    }

    private fun clickListener() {
        mBinding.toolbar.imgBack.setOnClickListener(this)

        mBinding.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
           override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override  fun onPageSelected(position: Int) {
                mBinding.viewPager.setCurrentItem(position, false)
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })

    }

    override fun onClick(v: View) {
        super.onClick(v)
        when (v.id) {
            R.id.img_back -> activity?.onBackPressed()
        }
    }
    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(activity?.supportFragmentManager!!)
        adapter.addFragment(BatteryFrm(), "Battery")
        adapter.addFragment(BluetoothFrm(), "Bluetooth")
        adapter.addFragment(CameraFrm(), "Camera")
        adapter.addFragment(DeviceDetailsFrm(), "Details")
        adapter.addFragment(DeviceFeaturesFrm(), "Feature")
        adapter.addFragment(DeviceNetworkFrm(), "Network")
        adapter.addFragment(DisplayFrm(), "Display")
        adapter.addFragment(OSFrm(), "OS")
        adapter.addFragment(SimFrm(), "SIM")
        adapter.addFragment(StorageFrm(), "Storage")
        adapter.addFragment(UserAppFrm.getInstance(IS_USER_COME_FROM_SYSTEM_APPS), "System App")
        adapter.addFragment(UserAppFrm.getInstance(IS_USER_COME_FROM_USER_APPS), "User App")
        adapter.addFragment(ProcessorFrm(), "Processor")
        viewPager.adapter = adapter
    }
}
