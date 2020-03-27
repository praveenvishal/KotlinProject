package com.demo.kotlinproject.view.adapter

import androidx.databinding.ViewDataBinding
import com.demo.kotlinproject.R
import com.demo.kotlinproject.databinding.RowCouponListItemBinding
import com.demo.kotlinproject.model.response.CouponListResponse
import com.demo.kotlinproject.view.base.BaseAdapter


class CouponListAdapter(var list: ArrayList<CouponListResponse.Coupon>) : BaseAdapter() {
    override fun getListSize(): Int {
        if (list == null) return 0
        return list?.size!!
    }

    override fun getLayoutId(viewType: Int): Int {
        return R.layout.row_coupon_list_item
    }

    override fun onBindTo(mRowBinding: ViewDataBinding, position: Int) {
        if (mRowBinding is RowCouponListItemBinding) {
            val item= list[position]
            mRowBinding.textViewDescription.setText(item.description)
            mRowBinding.textViewOffer.setText(item.price)
        }
    }

    fun setCupons(cupons: ArrayList<CouponListResponse.Coupon>) {
        list.clear()
        list.addAll(cupons)
        notifyDataSetChanged()
    }


}