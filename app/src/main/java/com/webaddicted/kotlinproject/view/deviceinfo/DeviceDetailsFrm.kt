package com.webaddicted.kotlinproject.view.deviceinfo

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.text.Html
import android.util.DisplayMetrics
import android.view.View
import android.view.WindowManager
import androidx.databinding.ViewDataBinding
import com.webaddicted.kotlinproject.R
import com.webaddicted.kotlinproject.databinding.FrmDevBasicBinding
import com.webaddicted.kotlinproject.view.base.BaseFragment

class DeviceDetailsFrm : BaseFragment() {
    private lateinit var mBinding: FrmDevBasicBinding

    companion object {
        val TAG = DeviceDetailsFrm::class.java.simpleName
        fun getInstance(bundle: Bundle): DeviceDetailsFrm {
            val fragment = DeviceDetailsFrm()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun getLayout(): Int {
        return R.layout.frm_dev_basic
    }

    override fun initUI(binding: ViewDataBinding?, view: View) {
        mBinding = binding as FrmDevBasicBinding
        getDeviceInfo()
    }


    private fun getDeviceInfo() {
        mBinding.txtDeviceName.text = Build.BRAND
        mBinding.txtDeviceId.text = Build.MODEL
        @SuppressLint("HardwareIds") val androidID =
            Settings.Secure.getString(
                context!!.contentResolver,
                Settings.Secure.ANDROID_ID
            )
        val wm =
            activity?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay
        val metrics = DisplayMetrics()
        display.getMetrics(metrics)
        val width = metrics.widthPixels
        val height = metrics.heightPixels
        val deviceInfo = "<font color=\"#000000\">Manufacturer : </font>${Build.MANUFACTURER}<br>" +
                "<font color=\"#000000\">Hardware : </font>${Build.HARDWARE}<br>" +
                "<font color=\"#000000\">Board : </font>${Build.BOARD}<br>" +
                "<font color=\"#000000\">Serial : </font>${Build.SERIAL}<br>" +
                "<font color=\"#000000\">Android Id : </font>${androidID}<br>" +
                "<font color=\"#000000\">ScreenResolution : </font>$width * $height Pixels<br>" +
                "<font color=\"#000000\">BootLoader : </font>${Build.BOOTLOADER}<br>" +
                "<font color=\"#000000\">Host : </font>${Build.HOST}<br>" +
                "<font color=\"#000000\">User : </font>${Build.USER}<br>"
        mBinding.txtDeviceInfo.text = Html.fromHtml(deviceInfo)
    }
}
