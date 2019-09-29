package com.webaddicted.kotlinproject.view.adapter

import android.app.Activity
import android.view.View
import androidx.databinding.ViewDataBinding
import com.android.boxlty.global.common.showImage
import com.webaddicted.kotlinproject.R
import com.webaddicted.kotlinproject.databinding.RowLanguageBinding
import com.webaddicted.kotlinproject.model.bean.language.LanguageBean
import com.webaddicted.kotlinproject.view.base.BaseAdapter
import com.webaddicted.kotlinproject.view.activity.LanguageActivity

/**
 * Created by Deepak Sharma on 01/07/19.
 */
class LanguageAdapter(
    private val activity: Activity,
    private val languageList: List<LanguageBean>
) : BaseAdapter() {
    var selectedPos = -1
    override fun getListSize(): Int {
//        var size =  (languageList == null || languageList.size == 0) ? 0 languageList.size;
        return languageList?.size
    }

    override fun getLayoutId(viewType: Int): Int {
        return R.layout.row_language
    }

    override fun onBindTo(rowBinding: ViewDataBinding, position: Int) {
        if (rowBinding is RowLanguageBinding) {
            val mRowBinding = rowBinding as RowLanguageBinding
            var source = languageList.get(position)
            mRowBinding.txtLanguageName.text = source.languageName
            mRowBinding.imgCountryFlag.showImage(
                source.languageFlag,
                getPlaceHolder(1)
            );
            if (selectedPos == position) mRowBinding.rbLanguage.isChecked = true
            else mRowBinding.rbLanguage.isChecked = false
            onClickListener(mRowBinding.rbLanguage, position)
        }
    }

    override fun getClickEvent(view: View?, position: Int) {
        super.getClickEvent(view, position)
        when (view?.id) {
            R.id.rb_language -> {
                selectedPos = position
                (activity as LanguageActivity).languageObserver(selectedPos)
                notifyDataSetChanged()
            }
        }
    }
}