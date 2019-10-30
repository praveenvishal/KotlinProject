package com.webaddicted.kotlinproject.view.adapter

import android.view.View
import androidx.databinding.ViewDataBinding
import com.webaddicted.kotlinproject.R
import com.webaddicted.kotlinproject.databinding.RowGridBinding
import com.webaddicted.kotlinproject.databinding.RowRecyclerListBinding
import com.webaddicted.kotlinproject.databinding.RowTextListBinding
import com.webaddicted.kotlinproject.global.common.showImage
import com.webaddicted.kotlinproject.view.base.BaseAdapter
import com.webaddicted.kotlinproject.view.fragment.RecyclerViewFrm

/**
 * Created by Deepak Sharma on 01/07/19.
 */
class RecyclerListAdapter(
    private val recyclerViewFrm: RecyclerViewFrm,
    private var dataList: List<String>?,
    private val layoutId: Int
) : BaseAdapter() {

    override fun getListSize(): Int? {
//        var size =  (languageList == null || languageList.size == 0) ? 0 languageList.size;
        return dataList?.size
    }

    override fun getLayoutId(viewType: Int): Int {
        return if (viewType == itemCount - 1) return layoutId
        else return R.layout.row_text_list
    }

    private object VIEW_TYPES {
        val NORMAL = 1
        val FOOTER = 2
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == itemCount - 1) VIEW_TYPES.FOOTER
        else VIEW_TYPES.NORMAL
    }

    override fun onBindTo(rowBinding: ViewDataBinding, position: Int) {
        if (rowBinding is RowRecyclerListBinding) {
            val mRowBinding = rowBinding as RowRecyclerListBinding
            var source = dataList?.get(position)
            mRowBinding.imgInitial.showImage(
                source,
                getPlaceHolder(0)
            );
            onClickListener(mRowBinding.imgInitial, position)
        } else if (rowBinding is RowGridBinding) {
            val mRowBinding = rowBinding as RowGridBinding
            var source = dataList?.get(position)
            mRowBinding.img.showImage(
                source,
                getPlaceHolder(0)
            );
            onClickListener(mRowBinding.img, position)
        } else if (rowBinding is RowTextListBinding) {
            val mRowBinding = rowBinding as RowTextListBinding
        }
    }

    override fun getClickEvent(view: View?, position: Int) {
        super.getClickEvent(view, position)
        when (view?.id) {
            R.id.img_initial -> {
            }
        }
    }

    fun notifyAdapter(list: ArrayList<String>?) {
        dataList = list
        notifyDataSetChanged()
    }
}