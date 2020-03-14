package com.webaddicted.kotlinproject.view.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.PopupMenu
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.webaddicted.kotlinproject.R
import com.webaddicted.kotlinproject.databinding.FrmTaskListBinding
import com.webaddicted.kotlinproject.global.annotationdef.SortListType
import com.webaddicted.kotlinproject.global.common.*
import com.webaddicted.kotlinproject.view.activity.*
import com.webaddicted.kotlinproject.view.adapter.TaskAdapter
import com.webaddicted.kotlinproject.view.base.BaseFragment
import com.webaddicted.kotlinproject.view.deviceinfo.DeviceInfoFrm
import com.webaddicted.kotlinproject.view.ecommerce.EcommLoginFrm
import java.util.*
import kotlin.collections.ArrayList

class TaskFrm : BaseFragment() {
    private lateinit var mBinding: FrmTaskListBinding
    private lateinit var mHomeAdapter: TaskAdapter
    private var mTaskList: ArrayList<String>? = ArrayList()
    private var worktask = arrayOf(
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
        "Expendable/Spinner List View",
        "Share",
        "Receiver",
        "Services",
        "Viewpager Tab",
        "FingerPrint",
        "Barcode",
        "Timer",
        "BlinkScan",
        "Ecommerce",
        "Navigation Drawer Both Side",
        "Navigation Drawer",
        "ScreenShot",
        "Digital Signature",
        "PDF",
        "Collapse",
        "UI Design",
        "Fab Button",
        "Bottom Navigation",
        "Bottom Sheet",
        "Coroutines",
        "Splash"

    )
    lateinit var showSearchView: ShowSearchView

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
        mBinding.toolbar.imgProfile.visible()
        mBinding.toolbar.imgSort.visible()
        mBinding.toolbar.imgProfile.setImageDrawable(
            ResourcesCompat.getDrawable(
                resources,
                R.drawable.ic_search,
                null
            )
        )
        mBinding.toolbar.txtToolbarTitle.text = resources.getString(R.string.task_title)
        mTaskList = ArrayList(listOf(*worktask))
        showSearchView = ShowSearchView()
        setAdapter()
        clickListener()
        sortList(SortListType.ASCENDING)
        val currentMode = AppCompatDelegate.getDefaultNightMode()
        if (currentMode == AppCompatDelegate.MODE_NIGHT_YES)
            activity?.showToast("Night Mode")
        else if (currentMode == AppCompatDelegate.MODE_NIGHT_NO)
            activity?.showToast("Day Mode")
    }

    private fun clickListener() {
        mBinding.toolbar.imgProfile.setOnClickListener(this)
        mBinding.toolbar.imgSearchBack.setOnClickListener(this)
        mBinding.toolbar.imgSort.setOnClickListener(this)
        mBinding.linearMobileNo.setOnClickListener(this)
        mBinding.toolbar.editTextSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                val text = mBinding.toolbar.editTextSearch.text.toString()
                    .toLowerCase(Locale.getDefault())
                mHomeAdapter.filter(text)
            }

            override fun afterTextChanged(editable: Editable) {
            }
        })
    }

    override fun onClick(v: View) {
        super.onClick(v)
        when (v.id) {
            R.id.img_search_back, R.id.img_profile ->
                showSearchView.handleToolBar(
                    activity,
                    mBinding.toolbar.cardSearch,
                    mBinding.toolbar.editTextSearch
                )
            R.id.linear_mobile_no -> {
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:" + getString(R.string.deep_mobile_no))
                startActivity(intent)
            }
            R.id.img_sort -> showPopupMenu(mBinding.toolbar.imgSort)
        }
    }

    private fun setAdapter() {
        mHomeAdapter = TaskAdapter(this@TaskFrm, mTaskList)
        mBinding.recyclerView.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.VERTICAL,
            false
        )
        mBinding.recyclerView.adapter = mHomeAdapter
    }

    fun onClicks(click: String) {
        when (click) {
            "Widgets" -> navigateScreen(WidgetFrm.TAG)
            "News Api" -> navigateScreen(NewsFrm.TAG)
            "Google Map / Location" -> navigateScreen(MapActivity.TAG)
            "Circle Game" -> navigateScreen(CircleFrm.TAG)
            "Calendar View" -> navigateScreen(CalendarFrm.TAG)
            "SMS Retriever" -> navigateScreen(SmsRetrieverFrm.TAG)
            "Webview" -> navigateScreen(WebViewActivity.TAG)
            "Dialog" -> navigateScreen(DialogFrm.TAG)
            "Select Multiple Image" -> navigateScreen(ProfileFrm.TAG)
            "Dynamic Layout" -> navigateScreen(DynamicLayoutFrm.TAG)
            "Shared Preference" -> navigateScreen(SharedPrefFrm.TAG)
            "Speech to text" -> navigateScreen(SpeechTextActivity.TAG)
            "Animation" -> navigateScreen(AnimationFrm.TAG)
            "Share" -> navigateScreen(ShareDataFrm.TAG)
            "Recycler View" -> navigateScreen(RecyclerViewFrm.TAG)
            "Expendable/Spinner List View" -> navigateScreen(ExpendableFrm.TAG)
            "Receiver" -> navigateScreen(ReceiverFrm.TAG)
            "Services" -> navigateScreen(ServiceFrm.TAG)
            "Ecommerce" -> navigateScreen(EcommLoginFrm.TAG)
            "Timer" -> navigateScreen(TimerFrm.TAG)
            "Navigation Drawer Both Side" -> navigateScreen(NavBothSideDrawerActivity.TAG)
            "Navigation Drawer" -> navigateScreen(NavieDrawerActivity.TAG)
            "BlinkScan" -> navigateScreen(BlinkScanFrm.TAG)
            "Coroutines" -> navigateScreen(CoroutineFrm.TAG)
            "ScreenShot" -> checkStoragePermission()
            "Splash" -> navigateScreen(SplashActivity.TAG)
            "Device Info" -> navigateScreen(DeviceInfoFrm.TAG)
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
            MapActivity.TAG -> activity?.let { MapActivity.newIntent(it) }
            CircleFrm.TAG -> frm = CircleFrm.getInstance(Bundle())
            CalendarFrm.TAG -> frm = CalendarFrm.getInstance(Bundle())
            SmsRetrieverFrm.TAG -> frm = SmsRetrieverFrm.getInstance(Bundle())
            WebViewActivity.TAG -> activity?.let { WebViewActivity.newIntent(it) }
            DialogFrm.TAG -> frm = DialogFrm.getInstance(Bundle())
            ProfileFrm.TAG -> frm = ProfileFrm.getInstance(Bundle())
            DynamicLayoutFrm.TAG -> frm = DynamicLayoutFrm.getInstance(Bundle())
            SharedPrefFrm.TAG -> frm = SharedPrefFrm.getInstance(Bundle())
            AnimationFrm.TAG -> frm = AnimationFrm.getInstance(Bundle())
            ShareDataFrm.TAG -> frm = ShareDataFrm.getInstance(Bundle())
            SpeechTextActivity.TAG -> activity?.let { SpeechTextActivity.newIntent(it) }
            RecyclerViewFrm.TAG -> frm = RecyclerViewFrm.getInstance(Bundle())
            ExpendableFrm.TAG -> frm = ExpendableFrm.getInstance(Bundle())
            ReceiverFrm.TAG -> frm = ReceiverFrm.getInstance(Bundle())
            ServiceFrm.TAG -> frm = ServiceFrm.getInstance(Bundle())
            EcommLoginFrm.TAG -> frm = EcommLoginFrm.getInstance(Bundle())
            TimerFrm.TAG -> frm = TimerFrm.getInstance(Bundle())
            BlinkScanFrm.TAG -> frm = BlinkScanFrm.getInstance(Bundle())
            NavBothSideDrawerActivity.TAG -> activity?.let { NavBothSideDrawerActivity.newIntent(it) }
            NavieDrawerActivity.TAG -> activity?.let { NavieDrawerActivity.newIntent(it) }
            CoroutineFrm.TAG -> frm = CoroutineFrm.getInstance(Bundle())
            SplashActivity.TAG -> activity?.let { SplashActivity.newIntent(it) }
            DeviceInfoFrm.TAG -> frm = DeviceInfoFrm.getInstance(Bundle())
            else -> frm = WidgetFrm.getInstance(Bundle())
        }
        frm?.let { navigateAddFragment(R.id.container, it, true) }
    }

    //  Tooltip -> https://github.com/skydoves/Balloon/blob/master/app/src/main/java/com/skydoves/balloondemo/BalloonUtils.kt

    override fun onPermissionGranted(mCustomPermission: List<String>) {
        super.onPermissionGranted(mCustomPermission)
        GlobalUtility.captureScreen(activity!!)
        activity?.showToast("Screen capture successfully")
    }

    private fun sortList(@SortListType.SortType sortType: Int) {
        when (sortType) {
            SortListType.DEFAULT -> mTaskList = ArrayList(listOf(*worktask))
            SortListType.ASCENDING -> mTaskList?.sort()
            SortListType.DESCENDING -> Collections.sort(mTaskList, Collections.reverseOrder())
        }
        if (mHomeAdapter != null) mTaskList?.let { mHomeAdapter.notifyAdapter(it) }
    }

    private fun showPopupMenu(view: View) { // inflate menu
        val popup = PopupMenu(activity!!, view)
        val inflater: MenuInflater = popup.menuInflater
        inflater.inflate(R.menu.menu_sort, popup.menu)
        popup.setOnMenuItemClickListener(MyMenuItemClickListen(this))
        popup.show()
    }

    internal class MyMenuItemClickListen(var taskFrm: TaskFrm) : PopupMenu.OnMenuItemClickListener {
        override fun onMenuItemClick(menuItem: MenuItem): Boolean {
            when (menuItem.itemId) {
                R.id.menu_default -> taskFrm.sortList(SortListType.DEFAULT)
                R.id.menu_ascending -> taskFrm.sortList(SortListType.ASCENDING)
                R.id.menu_descending -> taskFrm.sortList(SortListType.DESCENDING)
            }
            return false
        }
    }

    override fun onResume() {
        super.onResume()
        addBlankSpace(mBinding.bottomSpace)
    }
}
