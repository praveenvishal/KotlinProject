package com.webaddicted.kotlinproject.view.adapter

import android.graphics.Paint
import android.view.View
import androidx.databinding.ViewDataBinding
import com.webaddicted.kotlinproject.R
import com.webaddicted.kotlinproject.databinding.RowEcomCateBinding
import com.webaddicted.kotlinproject.databinding.RowProductCatBinding
import com.webaddicted.kotlinproject.global.common.gone
import com.webaddicted.kotlinproject.global.common.showImage
import com.webaddicted.kotlinproject.global.common.visible
import com.webaddicted.kotlinproject.model.bean.ecommerce.EcommCateBean
import com.webaddicted.kotlinproject.view.base.BaseAdapter

/**
 * Created by Deepak Sharma on 01/07/19.
 */
class EcomProductCatAdapter() : BaseAdapter() {
    override fun getListSize(): Int {
//        if (list == null) return 0
        return 25//list?.size!!

    }

    override fun getLayoutId(viewType: Int): Int {
        return R.layout.row_product_cat
    }

    override fun onBindTo(rowBinding: ViewDataBinding, position: Int) {
        if (rowBinding is RowProductCatBinding) {
            val mRowBinding = rowBinding as RowProductCatBinding
            mRowBinding.imgProduct.setImageResource(R.drawable.iphnx)
            mRowBinding.offer.setText("\u20B9 63,999")
            mRowBinding.offer.setPaintFlags(mRowBinding.offer.getPaintFlags() or Paint.STRIKE_THRU_TEXT_FLAG)
            if ((position == 0) or (position == 1)) mRowBinding.text.visible()
            else mRowBinding.text.gone()
        }
    }

//    fun notifyAdapter(prodList: ArrayList<EcommCateBean>) {
//        list = prodList
//        notifyDataSetChanged()
//    }
}