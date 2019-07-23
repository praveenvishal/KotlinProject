package com.example.kotlinproject.viewModel.common

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinproject.global.common.AppApplication
import com.example.kotlinproject.model.repository.news.NewsRepository
import com.example.kotlinproject.model.respo.newsChannel.NewsChanelRespo
import com.prodege.shopathome.model.networkCall.ApiResponse
import org.koin.core.KoinComponent

/**
 * Created by Deepak Sharma on 01/07/19.
 */
class CommonViewModel :ViewModel(){
    public var channelResponse: String?= "hiiiiiii"

}