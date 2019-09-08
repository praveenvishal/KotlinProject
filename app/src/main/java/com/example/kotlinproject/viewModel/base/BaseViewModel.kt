package com.example.kotlinproject.viewModel.base

import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinproject.global.common.AppApplication
import com.example.kotlinproject.global.sharedPref.PreferenceMgr
import com.example.kotlinproject.model.repository.news.NewsRepository
import com.example.kotlinproject.model.bean.newsChannel.NewsChanelRespo
import com.prodege.shopathome.model.networkCall.ApiResponse
import org.koin.core.KoinComponent
import org.koin.core.inject

/**
 * Created by Deepak Sharma on 01/07/19.
 */
open class BaseViewModel() :ViewModel(), KoinComponent {
    protected val preferenceMgr: PreferenceMgr by inject()
}