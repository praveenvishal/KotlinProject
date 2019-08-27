package com.example.kotlinproject.view.adapter

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.databinding.ViewDataBinding
import com.example.kotlinproject.R
import com.example.kotlinproject.databinding.RowChannelListBinding
import com.example.kotlinproject.databinding.RowTextListBinding
import com.example.kotlinproject.global.common.GlobalUtility.Companion.showImageUsingGLIDE
import com.example.kotlinproject.model.respo.newsChannel.NewsChanelRespo
import com.example.kotlinproject.view.base.BaseAdapter
import com.example.kotlinproject.view.task.TaskFrm
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by Deepak Sharma on 01/07/19.
 */
class TaskAdapter(private var taskFrm: TaskFrm, var mTaskList: ArrayList<String>?) : BaseAdapter() {
    public var searchText: String? = null
    private val searchArray: ArrayList<String>

    init {
        this.searchArray = java.util.ArrayList<String>()
        mTaskList?.let { this.searchArray.addAll(it) }
    }

    override fun getListSize(): Int {
        if (mTaskList == null) return 0
        return mTaskList?.size!!
    }

    override fun getLayoutId(viewType: Int): Int {
        return R.layout.row_text_list
    }

    override fun onBindTo(rowBinding: ViewDataBinding, position: Int) {
        if (rowBinding is RowTextListBinding) {
            val mRowBinding = rowBinding as RowTextListBinding
//            if (searchText != null && searchText?.length!! > 1) {
//                val completeText = mTaskList?.get(position)
//                val spannableString = SpannableString(mTaskList?.get(position))
//                var i = -1
//                while ((i = completeText.indexOf(searchText!!, i + 1)) != -1) {
//                    val endText = searchText?.length!! + i
//                    val foregroundSpan = ForegroundColorSpan(Color.GREEN)
//                    spannableString.setSpan(
//                        foregroundSpan,
//                        i,
//                        endText,
//                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
//                    )
//                    i++
//                }
            mRowBinding.txtName.setText(mTaskList?.get(position))
//            } else {
//                mRowBinding.txtName.setText(mTaskList?.get(position))
//            }
        }
    }

    //    fun notifyAdapter(newsBeanList: ArrayList<NewsChanelRespo.Source>) {
//        newsList = newsBeanList
//        notifyDataSetChanged()
//    }
    fun filter(charText: String?) {
        var charText = charText
        charText = charText!!.toLowerCase(Locale.getDefault())
        searchText = charText
        mTaskList?.clear()
        if (charText == null && charText!!.length == 0) {
            mTaskList?.addAll(searchArray)
        } else {
            for (wp in searchArray) {
                if (wp != null && wp!!.toLowerCase(Locale.getDefault()).contains(charText)) {
                    mTaskList?.add(wp)
                }
                //                else if (wp.toLowerCase(Locale.getDefault()).contains(charText)) {
                //                    mAction.add(wp);
                //                }
            }
        }
        notifyDataSetChanged()
    }
}