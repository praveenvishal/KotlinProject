package com.demo.kotlinproject.model.repository.news

import androidx.lifecycle.MutableLiveData
import com.demo.kotlinproject.apiutils.ApiResponse
import com.demo.kotlinproject.apiutils.ApiServices
import com.demo.kotlinproject.apiutils.DataFetchCall
import com.demo.kotlinproject.model.response.BaseApiResponse
import com.demo.kotlinproject.model.response.CouponListResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response

class NewsRepository constructor(private val apiServices: ApiServices) : BaseRepository() {
    fun getCoupons(strUrl: String, apiResponse: MutableLiveData<ApiResponse<BaseApiResponse<CouponListResponse>>>) {
        object : DataFetchCall<BaseApiResponse<CouponListResponse>>(apiResponse) {
            override fun createCallAsync(): Deferred<Response<BaseApiResponse<CouponListResponse>>> {
                return apiServices.getCouponList(strUrl)
            }
            override fun shouldFetchFromDB(): Boolean {
                return false
            }
        }.execute()
    }
}