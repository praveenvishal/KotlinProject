package com.demo.kotlinproject.viewModel.home

import androidx.lifecycle.MutableLiveData
import com.demo.kotlinproject.model.repository.news.NewsRepository
import com.demo.kotlinproject.viewModel.base.BaseViewModel
import com.demo.kotlinproject.apiutils.ApiResponse
import com.demo.kotlinproject.model.response.BaseApiResponse
import com.demo.kotlinproject.model.response.CouponListResponse

class HomeViewModel(private val projectRepository: NewsRepository) :BaseViewModel() {
    private var couponsListResponse = MutableLiveData<ApiResponse<BaseApiResponse<CouponListResponse>>>()

    fun getCouponListResponse(): MutableLiveData<ApiResponse<BaseApiResponse<CouponListResponse>>> {
        return couponsListResponse
    }

    fun getCouponsList(strUrl: String) {
        projectRepository.getCoupons(strUrl,couponsListResponse)
    }

}