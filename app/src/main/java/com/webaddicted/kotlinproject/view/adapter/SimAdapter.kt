package com.webaddicted.kotlinproject.view.adapter

import androidx.databinding.ViewDataBinding
import com.webaddicted.kotlinproject.R
import com.webaddicted.kotlinproject.databinding.RowSimItemBinding
import com.webaddicted.kotlinproject.model.bean.deviceinfo.SimInfo
import com.webaddicted.kotlinproject.view.base.BaseAdapter

/**
 * Created by Deepak Sharma on 01/07/19.
 */
class SimAdapter(private var list: java.util.ArrayList<SimInfo>?) : BaseAdapter() {
    override fun getListSize(): Int {
        if (list == null) return 0
        return list?.size!!

    }

    override fun getLayoutId(viewType: Int): Int {
        return R.layout.row_sim_item
    }

    override fun onBindTo(rowBinding: ViewDataBinding, position: Int) {
        if (rowBinding is RowSimItemBinding) {
            val mRowBinding = rowBinding as RowSimItemBinding
            var source = list?.get(position)
            mRowBinding.tvLabel.setText(source?.simLable)
            mRowBinding.tvSimInformation.setText(source?.simData)
        }
    }

    fun notifyAdapter(prodList: ArrayList<SimInfo>) {
        list = prodList
        notifyDataSetChanged()
    }
}