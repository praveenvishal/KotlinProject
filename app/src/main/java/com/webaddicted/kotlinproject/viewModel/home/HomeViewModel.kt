package com.webaddicted.kotlinproject.viewModel.home

import androidx.lifecycle.MutableLiveData
import com.webaddicted.kotlinproject.model.repository.news.NewsRepository
import com.webaddicted.kotlinproject.model.bean.newsChannel.NewsChanelRespo
import com.webaddicted.kotlinproject.viewModel.base.BaseViewModel
import com.prodege.shopathome.model.networkCall.ApiResponse

/**
 * Created by Deepak Sharma on 01/07/19.
 */
class HomeViewModel(private val projectRepository: NewsRepository) :BaseViewModel() {
    private var channelResponse = MutableLiveData<ApiResponse<NewsChanelRespo>>()

    fun getNewsChannelLiveData(): MutableLiveData<ApiResponse<NewsChanelRespo>> {
        return channelResponse
    }

    fun newsChannelApi(strUrl: String) {
        projectRepository.getNewsChannel(strUrl, channelResponse)
    }

}