package com.webaddicted.kotlinproject.view.adapter

import android.app.Activity
import android.view.View
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.ViewDataBinding
import com.webaddicted.kotlinproject.R
import com.webaddicted.kotlinproject.databinding.RowAppsBinding
import com.webaddicted.kotlinproject.databinding.RowSimItemBinding
import com.webaddicted.kotlinproject.global.common.GlobalUtility
import com.webaddicted.kotlinproject.model.bean.deviceinfo.DeviceInfo
import com.webaddicted.kotlinproject.model.bean.deviceinfo.SimInfo
import com.webaddicted.kotlinproject.view.base.BaseAdapter

/**
 * Created by Deepak Sharma on 01/07/19.
 */
class AppsAdapter(private var activity:Activity, private var appType:Int,private var list: ArrayList<DeviceInfo>?) : BaseAdapter() {
    override fun getListSize(): Int {
        if (list == null) return 0
        return list?.size!!

    }

    override fun getLayoutId(viewType: Int): Int {
        return R.layout.row_apps
    }

    override fun onBindTo(rowBinding: ViewDataBinding, position: Int) {
        if (rowBinding is RowAppsBinding) {
            val mRowBinding = rowBinding as RowAppsBinding
            var source = list?.get(position)
            mRowBinding.tvAppName.setText(source?.appLable)
            mRowBinding.tvAppPackageName.setText(source?.packageName)
            mRowBinding.ivAppIcon?.setImageDrawable(source?.appLogo)
            onClickListener(mRowBinding, mRowBinding.cardview, position)
        }
    }

    override fun getClickEvent(mRowBinding: ViewDataBinding,view: View?, position: Int) {
        super.getClickEvent(mRowBinding, view, position)
        mRowBinding as RowAppsBinding
        when(view?.id){
            R.id.cardview->{
                GlobalUtility.avoidDoubleClicks(mRowBinding.cardview)
                val launchIntent = activity.packageManager.getLaunchIntentForPackage(list?.get(position)?.packageName!!)
                if (launchIntent != null) {
                    startActivity(activity, launchIntent, null)
                }
            }
        }
    }
    fun notifyAdapter(prodList: ArrayList<DeviceInfo>) {
        list = prodList
        notifyDataSetChanged()
    }
}