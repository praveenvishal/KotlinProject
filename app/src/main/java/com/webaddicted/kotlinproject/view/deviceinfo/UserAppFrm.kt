package com.webaddicted.kotlinproject.view.deviceinfo

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import com.webaddicted.kotlinproject.R
import com.webaddicted.kotlinproject.databinding.FrmDevUserAppBinding
import com.webaddicted.kotlinproject.model.bean.deviceinfo.DeviceInfo
import com.webaddicted.kotlinproject.view.adapter.AppsAdapter
import com.webaddicted.kotlinproject.view.base.BaseFragment

class UserAppFrm : BaseFragment() {
    private val appList: ArrayList<DeviceInfo>? = null
    private lateinit var mAdapter: AppsAdapter
    private var appType: Int? = 0
    private lateinit var mBinding: FrmDevUserAppBinding

    companion object {
        val TAG = UserAppFrm::class.java.simpleName
        val APPS_TYPE = "AppInfo"
        fun getInstance(appsType: Int): UserAppFrm {
            val fragment = UserAppFrm()
            val bundle = Bundle()
            bundle.putInt(APPS_TYPE, appsType)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun getLayout(): Int {
        return R.layout.frm_dev_user_app
    }

    override fun onViewsInitialized(binding: ViewDataBinding?, view: View) {
        mBinding = binding as FrmDevUserAppBinding
        init()
        setAdapter()
//        showApiLoader()
        val lists = ArrayList<DeviceInfo>()
        getAppsList()?.filterTo(lists) { it.flags == appType }
        mAdapter.notifyAdapter(lists)
//        hideApiLoader()
    }

    private fun init() {
        appType = arguments?.getInt(APPS_TYPE)
    }

    private fun setAdapter() {
        mAdapter = AppsAdapter(activity!!, appType!!, appList)
        mBinding.rvApps.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.VERTICAL,
            false
        )
        mBinding.rvApps.adapter = mAdapter

    }

    fun getAppsList(): List<DeviceInfo>? {
        val deviceInfos: MutableList<DeviceInfo> =
            java.util.ArrayList()
        val flags =
            PackageManager.GET_META_DATA or PackageManager.GET_SHARED_LIBRARY_FILES
        val pm: PackageManager = activity?.packageManager!!
        val applications =
            pm.getInstalledApplications(flags)
        for (appInfo in applications) {
            if (appInfo.flags and ApplicationInfo.FLAG_SYSTEM == 1) { // System application
                val icon = pm.getApplicationIcon(appInfo)
                deviceInfos.add(
                    DeviceInfo(
                        1,
                        icon,
                        pm.getApplicationLabel(appInfo).toString(),
                        appInfo.packageName
                    )
                )
            } else { // Installed by User
                val icon = pm.getApplicationIcon(appInfo)
                deviceInfos.add(
                    DeviceInfo(
                        2,
                        icon,
                        pm.getApplicationLabel(appInfo).toString(),
                        appInfo.packageName
                    )
                )
            }
        }
        return deviceInfos
    }
}
