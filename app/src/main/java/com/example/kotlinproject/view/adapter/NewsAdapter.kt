package com.example.kotlinproject.view.adapter

import androidx.databinding.ViewDataBinding
import com.example.kotlinproject.R
import com.example.kotlinproject.databinding.RowChannelListBinding
import com.example.kotlinproject.global.common.GlobalUtility.Companion.showImageUsingGLIDE
import com.example.kotlinproject.model.respo.newsChannel.NewsChanelRespo
import com.example.kotlinproject.view.base.BaseAdapter

/**
 * Created by Deepak Sharma on 01/07/19.
 */
class NewsAdapter(private val newsList: ArrayList<NewsChanelRespo.Source>) : BaseAdapter() {
    override fun getListSize(): Int {
        return newsList.size
    }

    override fun getLayoutId(viewType: Int): Int {
        return R.layout.row_channel_list
    }

    override fun onBindTo(rowBinding: ViewDataBinding, position: Int) {
        if (rowBinding is RowChannelListBinding) {
            val mRowBinding = rowBinding as RowChannelListBinding
            var source = newsList.get(position)
            mRowBinding.txtChannelName.text = source.name
            mRowBinding.txtChannelDesc.text = source.description
            val stringBuilder = "https://besticon-demo.herokuapp.com/icon?url=" + source.url + "&size=64..64..120"
            showImageUsingGLIDE(stringBuilder, mRowBinding.imgChannelImg, getPlaceHolder(1));
        }
    }
}