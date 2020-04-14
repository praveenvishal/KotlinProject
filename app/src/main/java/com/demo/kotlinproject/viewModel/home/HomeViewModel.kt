package com.demo.kotlinproject.viewModel.home

import androidx.appcompat.widget.SearchView
import androidx.lifecycle.MutableLiveData
import com.demo.kotlinproject.data.api.response.BaseApiResponse
import com.demo.kotlinproject.data.api.response.CategoryResponse
import com.demo.kotlinproject.data.api.response.CouponListResponse
import com.demo.kotlinproject.data.api.response.SearchResponse
import com.demo.kotlinproject.data.api.util.ApiResponse
import com.demo.kotlinproject.data.repository.news.HomeRepository
import com.demo.kotlinproject.viewModel.base.BaseViewModel
import io.reactivex.subjects.PublishSubject

class HomeViewModel(private val projectRepository: HomeRepository) : BaseViewModel() {
    private var couponsListResponse =
        MutableLiveData<ApiResponse<BaseApiResponse<CouponListResponse>>>()
    private var searchListResponse = MutableLiveData<ApiResponse<SearchResponse>>()
    private var categoryListResponse =
        MutableLiveData<ApiResponse<CategoryResponse>>()

    fun getCouponListResponse(): MutableLiveData<ApiResponse<BaseApiResponse<CouponListResponse>>> {
        return couponsListResponse
    }

    fun getCategoriesListResponse(): MutableLiveData<ApiResponse<CategoryResponse>> {
        return categoryListResponse
    }

    fun getSearchListResponse(): MutableLiveData<ApiResponse<SearchResponse>> {
        return searchListResponse
    }

    fun getCouponsList(strUrl: String) {
        projectRepository.getCoupons(strUrl, couponsListResponse)
    }

    fun triggerSearchListApi(lat: String, long: String, sort: String) {
        projectRepository.searchApi(lat, long, sort, searchListResponse)
    }


    fun triggerCategoriesListApi() {
        projectRepository.getSearchCategories(categoryListResponse)
    }




}