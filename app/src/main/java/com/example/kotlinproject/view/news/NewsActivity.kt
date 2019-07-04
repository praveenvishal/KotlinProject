package com.example.kotlinproject.view.news

import android.app.ProgressDialog
import android.content.Intent
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinproject.R
import com.example.kotlinproject.view.base.BaseActivity
import com.example.kotlinproject.databinding.ActivityNewsBinding
import com.example.kotlinproject.global.common.GlobalUtility
import com.example.kotlinproject.global.sharedPref.PreferenceMgr
import com.example.kotlinproject.model.respo.newsChannel.NewsChanelRespo
import com.example.kotlinproject.view.adapter.NewsAdapter
import com.example.kotlinproject.view.profile.ProfileActivity
import com.example.kotlinproject.viewModel.list.NewsViewModel
import com.prodege.shopathome.model.networkCall.ApiResponse
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by Deepak Sharma on 01/07/19.
 */
class NewsActivity : BaseActivity() {
    private var progressDialog: ProgressDialog? = null
    private var mBinding: ActivityNewsBinding? = null
    private val preferenceMgr: PreferenceMgr  by inject()
    private val mViewModel: NewsViewModel by viewModel()
    private var newsAdapter: NewsAdapter?=null

    override fun getLayout(): Int {
        return R.layout.activity_news
    }

    override fun initUI(binding: ViewDataBinding) {
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

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.img_profile -> startActivity(Intent(this@NewsActivity, ProfileActivity::class.java))
        }
    }

    private fun callApi() {
        progressDialog =
            ProgressDialog.show(this@NewsActivity, getString(R.string.please_wait), getString(R.string.loading))
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
                if (progressDialog!!.isShowing) progressDialog!!.dismiss()
                newsAdapter = NewsAdapter(response.data!!.sources)
                mBinding?.rvNewsChannel?.layoutManager = LinearLayoutManager(this)
                mBinding!!.rvNewsChannel.adapter = newsAdapter
            }
            ApiResponse.Status.ERROR -> {
                if (progressDialog!!.isShowing) progressDialog!!.dismiss()
                GlobalUtility.showToast(getString(R.string.something_went_wrong))
            }
        }
    }
}
