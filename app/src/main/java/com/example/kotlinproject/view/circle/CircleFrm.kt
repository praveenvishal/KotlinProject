package com.example.kotlinproject.view.circle

import android.app.ProgressDialog
import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinproject.R
import com.example.kotlinproject.databinding.ActivityNewsBinding
import com.example.kotlinproject.global.common.GlobalUtility
import com.example.kotlinproject.global.constant.AppConstant
import com.example.kotlinproject.model.circle.CircleGameBean
import com.example.kotlinproject.model.language.LanguageBean
import com.example.kotlinproject.model.respo.newsChannel.NewsChanelRespo
import com.example.kotlinproject.view.adapter.CircleGameAdapter
import com.example.kotlinproject.view.adapter.NewsAdapter
import com.example.kotlinproject.view.base.BaseFragment
import com.example.kotlinproject.view.base.ScrollListener
import com.example.kotlinproject.view.profile.ProfileFragment
import com.example.kotlinproject.viewModel.list.NewsViewModel
import com.example.kotlinproject.viewModel.map.MapViewModel
import com.prodege.shopathome.model.networkCall.ApiResponse
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*
import kotlin.collections.ArrayList

class CircleFrm : BaseFragment() {
    private var newsList: ArrayList<NewsChanelRespo.Source>? = null
    private lateinit var mBinding: ActivityNewsBinding
    private lateinit var newsAdapter: CircleGameAdapter
    private var progressDialog: ProgressDialog? = null
    private val mViewModel: NewsViewModel by viewModel()

    private var mPageCount: Int = 1

    companion object {
        val TAG = CircleFrm::class.java.simpleName
        fun getInstance(bundle: Bundle): CircleFrm {
            val fragment = CircleFrm()
            fragment.setArguments(bundle)
            return CircleFrm()
        }
    }

    override fun getLayout(): Int {
        return R.layout.activity_news
    }

    override fun onViewsInitialized(binding: ViewDataBinding?, view: View) {
        mBinding = binding as ActivityNewsBinding
        init()
        clickListener();
        setAdapter();
    }

    private fun init() {
        mBinding?.toolbar?.imgBack?.visibility = View.VISIBLE
        mBinding?.toolbar?.txtToolbarTitle?.text = resources.getString(R.string.circle_title)
        mBinding.relativeParent.setBackgroundColor(resources.getColor(R.color.app_color))
    }

    private fun clickListener() {
        mBinding?.toolbar?.imgBack?.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        super.onClick(v)
        when (v?.id) {
            R.id.img_back -> activity?.onBackPressed()
        }
    }

    private fun setAdapter() {
        newsAdapter = CircleGameAdapter(setCircleGameBean())
        mBinding?.rvNewsChannel.layoutManager = LinearLayoutManager(activity)
        mBinding?.rvNewsChannel.adapter = newsAdapter
    }
    private fun setCircleGameBean(): ArrayList<CircleGameBean> {
        var languageBeanList = ArrayList<CircleGameBean>()
        languageBeanList.add(CircleGameBean().apply {
            id = "0"
            gameName= "default (" + Locale.getDefault().displayName.toLowerCase() + ")"
            gameImg = ""
        })
        languageBeanList.add(CircleGameBean().also {
            it.id = "1"
            it.gameName = "argentina"
            it.gameImg = "https://mirrorspectator.com/wp-content/uploads/2019/03/31WNPn82f2L._SX425_.jpg"
        })
        languageBeanList.add(CircleGameBean().apply {
            id = "2"
            gameName = "english"
            gameImg =
                "https://upload.wikimedia.org/wikipedia/en/thumb/a/aa/English_Language_Flag.png/640px-English_Language_Flag.png"
        })
        languageBeanList.add(CircleGameBean().apply {
            id = "3"
            gameName = "hindi"
            gameImg = "https://www.imediaethics.org/wp-content/uploads/archive/B_Image_4450.jpg"
        })
        languageBeanList.add(CircleGameBean().also {
            it.id = "1"
            it.gameName = "argentina"
            it.gameImg = "https://mirrorspectator.com/wp-content/uploads/2019/03/31WNPn82f2L._SX425_.jpg"
        })
        languageBeanList.add(CircleGameBean().apply {
            id = "2"
            gameName = "english"
            gameImg =
                "https://upload.wikimedia.org/wikipedia/en/thumb/a/aa/English_Language_Flag.png/640px-English_Language_Flag.png"
        })
        languageBeanList.add(CircleGameBean().apply {
            id = "3"
            gameName = "hindi"
            gameImg = "https://www.imediaethics.org/wp-content/uploads/archive/B_Image_4450.jpg"
        })

        return languageBeanList
    }


    /**
     * navigate on fragment
     * @param tag represent navigation activity
     */
    private fun navigateScreen(tag: String) {
        var frm: Fragment? = null
        if (tag == ProfileFragment.TAG)
            frm = ProfileFragment.getInstance(Bundle())
//          navigateFragment(R.id.container, frm!!, true)
        navigateAddFragment(R.id.container, frm!!, true);
    }

}


