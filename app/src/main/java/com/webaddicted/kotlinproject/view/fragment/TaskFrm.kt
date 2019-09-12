package com.webaddicted.kotlinproject.view.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.webaddicted.kotlinproject.R
import com.webaddicted.kotlinproject.databinding.FrmTaskListBinding
import com.webaddicted.kotlinproject.view.adapter.TaskAdapter
import com.webaddicted.kotlinproject.view.base.BaseFragment
import com.webaddicted.kotlinproject.view.activity.MapActivity
import java.util.*
import kotlin.collections.ArrayList

class TaskFrm : BaseFragment() {
    private lateinit var mBinding: FrmTaskListBinding
    private lateinit var mHomeAdapter: TaskAdapter
    private var mTaskList: ArrayList<String>? = null
    internal var worktask = arrayOf(
        "widgets",
        "news api",
        "google map",
        "circle game",
        "calendar view",
        "webview",
        "dialog",
        "location",
        "SMS retriever",
        "login signup flow",
        "select multiple image",
        "dynamic layout",
        "shared preference",
        "device info",
        "speech to text",
        "animation",
        "recycler view",
        "pagination",
        "expendable spinner list view",
        "image",
        "share",
        "receiver",
        "services",
        "sqlite data base",
        "viewpager tab",
        "finger print",
        "barcode",
        "digital signature",
        "pdf",
        "collapse",
        "ui design",
        "fab button",
        "bottom navigation",
        "api"
    )


    companion object {
        val TAG = TaskFrm::class.java.simpleName
        fun getInstance(bundle: Bundle): TaskFrm {
            val fragment = TaskFrm()
            fragment.setArguments(bundle)
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
        mBinding.toolbar.imgBack.visibility = View.GONE
        mBinding.toolbar.txtToolbarTitle?.text = resources.getString(R.string.task_title)
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
                mHomeAdapter?.filter(text)
                //                mHomeAdapter.getFilter().filter(charSequence.toString());
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
        if (click == "widgets")
            navigateScreen(WidgetFrm.TAG)
        else if (click == "news api")
            navigateScreen(NewsFrm.TAG)
        else if (click == "google map")
            activity?.let { MapActivity.newIntent(it) }
        else if (click == "circle game")
            navigateScreen(CircleFrm.TAG)
        else if (click == "calendar view"){
            navigateScreen(CalendarFrm.TAG)
        }else if (click == "SMS retriever"){
            navigateScreen(SmsRetrieverFrm.TAG)
        }
    }
//            navigateScreen(WebViewFragment.TAG)
//        else if (click == "login signup flow")
//            navigateScreen(SelectMultipleFileFragment.TAG)
//        else if (click == "select multiple image")
//            navigateScreen(SelectMultipleFileFragment.TAG)
//        else if (click == "location")
//            navigateScreen(GpsLocationFragment.TAG)
//        else if (click == "dialog")
//            navigateScreen(DialogFragment.TAG)
//        else if (click == "dynamic layout")
//            navigateScreen(DynamicLayoutFragment.TAG)
//        else if (click == "shared preference")
//            navigateScreen(SharePreferenceFragment.TAG)
//        else if (click == "device info")
//            navigateScreen(GetPhoneDetailFragment.TAG)
//        else if (click == "speech to text")
//            navigateScreen(SpeechToTextFragment.TAG)
//        else if (click == "animation")
//            navigateScreen(AnimationFragment.TAG)
//        else if (click == "recycler view")
//            navigateScreen(RecyclerViewFragment.TAG)
//        else if (click == "pagination")
//            navigateScreen(PaginationFragment.TAG)
//        else if (click == "expendable spinner list view")
//            navigateScreen(ExpendableSpinnerListFragment.TAG)
//        else if (click == "image")
//            navigateScreen(ImageFragment.TAG)
//        else if (click == "share")
//            navigateScreen(ShareFragment.TAG)
//        else if (click == "receiver")
//            navigateScreen(ReceiverFragment.TAG)
//        else if (click == "services")
//            navigateScreen(ServicesFragment.TAG)
//        else if (click == "google map")
//            navigateScreen(GoogleMapFragment.TAG)
//        else if (click == "sqlite data base")
//            navigateScreen(SqliteDataBaseFragment.TAG)
//        else if (click == "viewpager tab")
//            navigateScreen(ViewPagerTabFragment.TAG)
//        else if (click == "finger print")
//            navigateScreen(FingerPrintFragment.TAG)
//        else if (click == "barcode")
//            navigateScreen(BarCodeFragment.TAG)
//        else if (click == "digital signature")
//            navigateScreen(DigitalSignatureFragment.TAG)
//        else if (click == "pdf")
//            navigateScreen(PdfFragment.TAG)
//        else if (click == "collapse")
//            navigateScreen(CollpaseViewFragment.TAG)
//        else if (click == "ui design")
//            navigateScreen(UiDesignFragment.TAG)
//        else if (click == "fab button")
//            navigateScreen(FabFragment.TAG)
//        else if (click == "bottom navigation")
//            navigateScreen(BottomNaviFragment.TAG)
//        else if (click == "api") navigateScreen(ApiFragment.TAG)


    /**
     * navigate on fragment
     *
     * @param tag represent navigation activity
     */
    fun navigateScreen(tag: String) {
        var frm: Fragment? = null
        if (tag == WidgetFrm.TAG)
            frm = WidgetFrm.getInstance(Bundle())
        else if (tag == NewsFrm.TAG)
            frm = NewsFrm.getInstance(Bundle())
        else if (tag == CircleFrm.TAG)
            frm = CircleFrm.getInstance(Bundle())
        else if (tag == CalendarFrm.TAG)
            frm = CalendarFrm.getInstance(Bundle())
//        else if (tag == SelectMultipleFileFragment.TAG)
//            frm = SelectMultipleFileFragment.getInstance(arguments)
//        else if (tag == GpsLocationFragment.TAG)
//            frm = GpsLocationFragment.getInstance(arguments)
//        else if (tag == DialogFragment.TAG)
//            frm = DialogFragment.getInstance(arguments)
//        else if (tag == DynamicLayoutFragment.TAG)
//            frm = DynamicLayoutFragment.getInstance(arguments)
//        else if (tag == SharePreferenceFragment.TAG)
//            frm = SharePreferenceFragment.getInstance(arguments)
//        else if (tag == GetPhoneDetailFragment.TAG)
//            frm = GetPhoneDetailFragment.getInstance(arguments)
//        else if (tag == SpeechToTextFragment.TAG)
//            frm = SpeechToTextFragment.getInstance(arguments)
//        else if (tag == AnimationFragment.TAG)
//            frm = AnimationFragment.getInstance(arguments)
//        else if (tag == RecyclerViewFragment.TAG)
//            frm = RecyclerViewFragment.getInstance(arguments)
//        else if (tag == PaginationFragment.TAG)
//            frm = PaginationFragment.getInstance(arguments)
//        else if (tag == ExpendableSpinnerListFragment.TAG)
//            frm = ExpendableSpinnerListFragment.getInstance(arguments)
//        else if (tag == ImageFragment.TAG)
//            frm = ImageFragment.getInstance(arguments)
//        else if (tag == ShareFragment.TAG)
//            frm = ShareFragment.getInstance(arguments)
//        else if (tag == ReceiverFragment.TAG)
//            frm = ReceiverFragment.getInstance(arguments)
//        else if (tag == ServicesFragment.TAG)
//            frm = ServicesFragment.getInstance(arguments)
//        else if (tag == GoogleMapFragment.TAG)
//            frm = GoogleMapFragment.getInstance(arguments)
//        else if (tag == SqliteDataBaseFragment.TAG)
//            frm = SqliteDataBaseFragment.getInstance(arguments)
//        else if (tag == ViewPagerTabFragment.TAG)
//            frm = ViewPagerTabFragment.getInstance(arguments)
//        else if (tag == FingerPrintFragment.TAG)
//            frm = FingerPrintFragment.getInstance(arguments)
//        else if (tag == BarCodeFragment.TAG)
//            frm = BarCodeFragment.getInstance(arguments)
//        else if (tag == DigitalSignatureFragment.TAG)
//            frm = DigitalSignatureFragment.getInstance(arguments)
//        else if (tag == PdfFragment.TAG)
//            frm = PdfFragment.getInstance(arguments)
//        else if (tag == FabFragment.TAG)
//            frm = FabFragment.getInstance(arguments)
//        else if (tag == UiDesignFragment.TAG)
//            frm = UiDesignFragment.getInstance(arguments)
//        else if (tag == BottomNaviFragment.TAG)
//            frm = BottomNaviFragment.getInstance(arguments)
//        else if (tag == CollpaseViewFragment.TAG)
//            frm = CollpaseViewFragment.getInstance(arguments)
//        else if (tag == ApiFragment.TAG)
//            frm = ApiFragment.getInstance(arguments)
        frm?.let { navigateFragment(R.id.container, it, true) }
        //        navigateAddFragment(R.id.container, frm, true);
    }

    fun updateTitle() {
//        (activity as HomeActivity).hideBackBtn()
//        (activity as HomeActivity).setToolbarTitle(resources.getString(R.string.app_name))
    }




}



