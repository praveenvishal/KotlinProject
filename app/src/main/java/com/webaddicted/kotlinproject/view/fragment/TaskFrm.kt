package com.webaddicted.kotlinproject.view.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.boxlty.global.common.gone
import com.webaddicted.kotlinproject.R
import com.webaddicted.kotlinproject.databinding.FrmTaskListBinding
import com.webaddicted.kotlinproject.view.activity.MapActivity
import com.webaddicted.kotlinproject.view.activity.WebViewActivity
import com.webaddicted.kotlinproject.view.adapter.TaskAdapter
import com.webaddicted.kotlinproject.view.base.BaseFragment
import java.util.*
import kotlin.collections.ArrayList

class TaskFrm : BaseFragment() {
    private lateinit var mBinding: FrmTaskListBinding
    private lateinit var mHomeAdapter: TaskAdapter
    private var mTaskList: ArrayList<String>? = null
    internal var worktask = arrayOf(
        "widgets",
        "news api",
        "google map/location",
        "circle game",
        "calendar view",
        "SMS retriever",
        "webview",
        "dialog",
        "select multiple image",
        "dynamic layout",
        "shared preference",
        "device info",
        "speech to text",
        "animation",
        "recycler view",
        "expendable spinner list view",
        "image",
        "share",
        "receiver",
        "services",
        "viewpager tab",
        "finger print",
        "barcode",
        "digital signature",
        "pdf",
        "collapse",
        "ui design",
        "fab button",
        "bottom navigation",
        "bottom sheet"
    )


    companion object {
        val TAG = TaskFrm::class.java.simpleName
        fun getInstance(bundle: Bundle): TaskFrm {
            val fragment = TaskFrm()
            fragment.arguments = bundle
            return TaskFrm()
        }
    }

    override fun getLayout(): Int {
        return R.layout.frm_task_list
    }

    override fun onViewsInitialized(binding: ViewDataBinding?, view: View) {
        mBinding = binding as FrmTaskListBinding
        init()
    }

    private fun init() {
        mBinding.toolbar.imgBack.gone()
        mBinding.toolbar.txtToolbarTitle.text = resources.getString(R.string.task_title)
        mTaskList = ArrayList(Arrays.asList(*worktask))
        setAdapter()
        clickListener()
    }

    private fun clickListener() {
        mBinding.edtSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                val text = mBinding.edtSearch.getText().toString().toLowerCase(Locale.getDefault())
                mHomeAdapter.filter(text)
            }

            override fun afterTextChanged(editable: Editable) {

            }
        })
    }

    private fun setAdapter() {
        mHomeAdapter = TaskAdapter(this@TaskFrm, mTaskList)
        mBinding.recyclerView.setLayoutManager(
            LinearLayoutManager(
                activity,
                LinearLayoutManager.VERTICAL,
                false
            )
        )
        mBinding.recyclerView.setAdapter(mHomeAdapter)
    }

    fun onClicks(click: String) {
        when (click) {
            "widgets" -> navigateScreen(WidgetFrm.TAG)
            "news api" -> navigateScreen(NewsFrm.TAG)
            "google map/location" -> activity?.let { MapActivity.newIntent(it) }
            "circle game" -> navigateScreen(CircleFrm.TAG)
            "calendar view" -> navigateScreen(CalendarFrm.TAG)
            "SMS retriever" -> navigateScreen(SmsRetrieverFrm.TAG)
            "webview" -> activity?.let { WebViewActivity.newIntent(it) }
            "dialog" -> navigateScreen(DialogFrm.TAG)
            else -> navigateScreen(WidgetFrm.TAG)
        }
    }

    /**
     * navigate on fragment
     *
     * @param tag represent navigation activity
     */
    fun navigateScreen(tag: String) {
        var frm: Fragment? = null
        when (tag) {
            WidgetFrm.TAG -> frm = WidgetFrm.getInstance(Bundle())
            NewsFrm.TAG -> frm = NewsFrm.getInstance(Bundle())
            CircleFrm.TAG -> frm = CircleFrm.getInstance(Bundle())
            CalendarFrm.TAG -> frm = CalendarFrm.getInstance(Bundle())
            SmsRetrieverFrm.TAG -> frm = SmsRetrieverFrm.getInstance(Bundle())
            DialogFrm.TAG -> frm = DialogFrm.getInstance(Bundle())
            else -> frm = WidgetFrm.getInstance(Bundle())
        }
        frm?.let { navigateFragment(R.id.container, it, true) }
    }
}