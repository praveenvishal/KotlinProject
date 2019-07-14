package com.example.kotlinproject.view.news

import android.app.ProgressDialog
import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinproject.R
import com.example.kotlinproject.databinding.ActivityNewsBinding
import com.example.kotlinproject.global.common.GlobalUtility
import com.example.kotlinproject.global.sharedPref.PreferenceMgr
import com.example.kotlinproject.model.respo.newsChannel.NewsChanelRespo
import com.example.kotlinproject.view.adapter.NewsAdapter
import com.example.kotlinproject.view.base.BaseFragment
import com.example.kotlinproject.view.profile.ProfileFragment
import com.example.kotlinproject.viewModel.list.NewsViewModel
import com.prodege.shopathome.model.networkCall.ApiResponse
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsFragment : BaseFragment() {

    private lateinit var mBinding: ActivityNewsBinding
    private lateinit var newsAdapter: NewsAdapter
    private var progressDialog: ProgressDialog? = null
    private val preferenceMgr: PreferenceMgr  by inject()
    private val mViewModel: NewsViewModel by viewModel()
    companion object {
        val TAG = NewsFragment::class.java.simpleName
        fun getInstance(bundle: Bundle): NewsFragment {
            val fragment = NewsFragment()
            fragment.setArguments(bundle)
            return NewsFragment()
        }
    }
    override fun getLayout(): Int {
        return R.layout.activity_news
    }
    override fun onViewsInitialized(binding: ViewDataBinding?, view: View) {
        mBinding = binding as ActivityNewsBinding
        init()
        clickListener();
    }
    private fun init() {
        mBinding?.toolbar?.imgProfile?.visibility = View.VISIBLE
        mBinding?.toolbar?.txtToolbarTitle?.text = resources.getString(R.string.news_channel)
        callApi()
    }

    private fun clickListener() {
        mBinding?.toolbar?.imgProfile?.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        super.onClick(v)
        when (v?.id) {
            R.id.img_profile -> navigateScreen(ProfileFragment.TAG)
        }
    }
    /**
     * navigate on fragment
     *
     * @param tag represent navigation activity
     */
    private fun navigateScreen(tag: String) {
        var frm: Fragment? = null
        if (tag == ProfileFragment.TAG)
            frm = ProfileFragment.getInstance(Bundle())
//        navigateFragment(R.id.container, frm!!, true)
        navigateAddFragment(R.id.container, frm!!, true);
    }

    private fun callApi() {
        progressDialog =
            ProgressDialog.show(activity, getString(R.string.please_wait), getString(R.string.loading))
        mViewModel?.getNewsChannelLiveData()?.observe(this, channelObserver)
        mViewModel?.newsChannelApi("https://newsapi.org/v2/sources?language=en&pageSize=20&apiKey=" + getString(R.string.news_api_key))
    }

    private val channelObserver: Observer<ApiResponse<NewsChanelRespo>> by lazy {
        Observer { response: ApiResponse<NewsChanelRespo> -> handleLoginResponse(response) }
    }

    private fun handleLoginResponse(response: ApiResponse<NewsChanelRespo>) {
        when (response?.status) {
            ApiResponse.Status.LOADING -> {
            }
            ApiResponse.Status.SUCCESS -> {
                if (progressDialog!!.isShowing) progressDialog?.dismiss()
                newsAdapter = NewsAdapter(response.data!!.sources)
                mBinding?.rvNewsChannel.layoutManager = LinearLayoutManager(activity)
                mBinding?.rvNewsChannel.adapter = newsAdapter
            }
            ApiResponse.Status.ERROR -> {
                if (progressDialog!!.isShowing) progressDialog!!.dismiss()
                GlobalUtility.showToast(getString(R.string.something_went_wrong))
            }
        }
    }
}

