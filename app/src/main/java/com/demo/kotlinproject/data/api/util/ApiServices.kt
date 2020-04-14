package com.demo.kotlinproject.data.api.util

import com.demo.kotlinproject.data.api.response.*
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiServices {
    @GET
    fun getCouponList(@Url strUrl: String): Deferred<Response<BaseApiResponse<CouponListResponse>>>


    //for getting categories
    @Headers("user_key: 7f27385f932eb2189e7e1e6e54446331")
    @GET("api/v2.1/categories")
    fun searchCategories(): Deferred<Response<CategoryResponse>>

    @GET("/api/v2.1/restaurant")
    fun getRestaurantDetails(@Query("res_id") restaurantId: String): Deferred<Response<RestaurantResponse>>


    //for getting all details of restaurant using cityid or lattitude and longitude and collection id
    @Headers("user_key: 7f27385f932eb2189e7e1e6e54446331")
    @GET("api/v2.1/search")
    fun searchApi(
        @Query("lat") queryParameters1: String,
        @Query("lon") queryParams2: String,
        @Query("sort") queryParams3: String
    ): Deferred<Response<SearchResponse>>


    @GET("/api/v2.1/search")
    abstract fun getsearchResults(
        @Query("lat") latitude: Double?,
        @Query("lon") longitude: Double?,
        @Query("q") searchString: String,
        @Query("count") count: Int?,
        @Query("radius") radiusInMeters: Int?
    ): Deferred<Response<com.demo.kotlinproject.data.api.response.search.SearchResponse>>


}