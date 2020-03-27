package com.demo.kotlinproject.apiutils

import com.demo.kotlinproject.model.response.BaseApiResponse
import com.demo.kotlinproject.model.response.CouponListResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiServices {
    @GET
    fun getCouponList(@Url strUrl: String): Deferred<Response<BaseApiResponse<CouponListResponse>>>
}