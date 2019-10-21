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
        "Widgets",
        "News Api",
        "Google Map / Location",
        "Circle Game",
        "Calendar View",
        "SMS Retriever",
        "Webview",
        "Dialog",
        "Select Multiple Image",
        "Dynamic Layout",
        "Shared Preference",
        "Device Info",
        "Speech to text",
        "Animation",
        "Recycler View",
        "Expendable Spinner List View",
        "Image",
        "Share",
        "Receiver",
        "Services",
        "Viewpager Tab",
        "FingerPrint",
        "Barcode",
        "Digital Signature",
        "PDF",
        "Collapse",
        "UI Design",
        "Fab Button",
        "Bottom Navigation",
        "Bottom Sheet"
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
            "Widgets" -> navigateScreen(WidgetFrm.TAG)
            "News Api" -> navigateScreen(NewsFrm.TAG)
            "Google Map / Location" -> activity?.let { MapActivity.newIntent(it) }
            "Circle Game" -> navigateScreen(CircleFrm.TAG)
            "Calendar View" -> navigateScreen(CalendarFrm.TAG)
            "SMS Retriever" -> navigateScreen(SmsRetrieverFrm.TAG)
            "Webview" -> activity?.let { WebViewActivity.newIntent(it) }
            "Dialog" -> navigateScreen(DialogFrm.TAG)
            else -> navigateScreen(WidgetFrm.TAG)
        }
    }

    override fun onResume() {
        super.onResume()
        addBlankSpace(mBinding.bottomSpace)
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
        frm?.let { navigateAddFragment(R.id.container, it, true) }
    }
}