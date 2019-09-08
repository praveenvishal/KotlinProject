package com.example.kotlinproject.view.adapter

import android.app.Activity
import androidx.databinding.ViewDataBinding
import com.example.kotlinproject.R
import com.example.kotlinproject.databinding.RowLanguageBinding
import com.example.kotlinproject.global.common.GlobalUtility.Companion.showImageUsingGLIDE
import com.example.kotlinproject.model.bean.language.LanguageBean
import com.example.kotlinproject.view.base.BaseAdapter
import com.example.kotlinproject.view.language.LanguageActivity

/**
 * Created by Deepak Sharma on 01/07/19.
 */
class LanguageAdapter(private val activity: Activity, private val languageList: List<LanguageBean>) : BaseAdapter() {
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
            showImageUsingGLIDE(
                source.languageFlag,
                mRowBinding.imgCountryFlag,
                getPlaceHolder(1)
            );
            if (selectedPos == position) mRowBinding.rbLanguage.isChecked = true
            else mRowBinding.rbLanguage.isChecked = false
            mRowBinding.rbLanguage.setOnClickListener {
                selectedPos = position
                (activity as LanguageActivity).languageObserver(selectedPos)
                notifyDataSetChanged()
            }
        }
    }
}