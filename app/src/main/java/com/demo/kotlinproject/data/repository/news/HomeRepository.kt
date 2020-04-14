package com.demo.kotlinproject.data.repository.news

import androidx.appcompat.widget.SearchView
import androidx.lifecycle.MutableLiveData
import com.demo.kotlinproject.data.api.pojo.LocationCoordinates
import com.demo.kotlinproject.data.api.response.*
import com.demo.kotlinproject.data.api.util.ApiResponse
import com.demo.kotlinproject.data.api.util.ApiServices
import com.demo.kotlinproject.data.api.util.DataFetchCall
import io.reactivex.subjects.PublishSubject
import kotlinx.coroutines.Deferred
import retrofit2.Response

class HomeRepository constructor(private val apiServices: ApiServices) : BaseRepository() {
    fun getCoupons(
        strUrl: String,
        apiResponse: MutableLiveData<ApiResponse<BaseApiResponse<CouponListResponse>>>
    ) {
        object : DataFetchCall<BaseApiResponse<CouponListResponse>>(apiResponse) {
            override fun createCallAsync(): Deferred<Response<BaseApiResponse<CouponListResponse>>> {
                return apiServices.getCouponList(strUrl)
            }

            override fun shouldFetchFromDB(): Boolean {
                return false
            }
        }.execute()
    }

    fun searchApi(
        lat: String,
        long: String,
        sort: String,
        apiResponse: MutableLiveData<ApiResponse<SearchResponse>>
    ) {
        object : DataFetchCall<SearchResponse>(apiResponse) {
            override fun createCallAsync(): Deferred<Response<SearchResponse>> {
                return apiServices.searchApi(lat, long, sort);
            }

            override fun shouldFetchFromDB(): Boolean {
                return false
            }
        }.execute()
    }

    fun getSearchCategories(apiResponse: MutableLiveData<ApiResponse<CategoryResponse>>) {
        object : DataFetchCall<CategoryResponse>(apiResponse) {
            override fun createCallAsync(): Deferred<Response<CategoryResponse>> {
                return apiServices.searchCategories()
            }

            override fun shouldFetchFromDB(): Boolean {
                return false
            }
        }.execute()
    }


    fun searchRestaurants(
        apiResponse: MutableLiveData<ApiResponse<com.demo.kotlinproject.data.api.response.search.SearchResponse>>,
        searchText: String,
        locationCoordinates: LocationCoordinates
    ) {
        object : DataFetchCall<com.demo.kotlinproject.data.api.response.search.SearchResponse>(
            apiResponse
        ) {
            override fun createCallAsync(): Deferred<Response<com.demo.kotlinproject.data.api.response.search.SearchResponse>> {
                return apiServices.getsearchResults(
                    locationCoordinates.getLatitude(),
                    locationCoordinates.getLongitude(), searchText, 20, 5000
                )
            }

            override fun shouldFetchFromDB(): Boolean {
                return false
            }
        }.execute()
    }


    fun getRestaurantDetails(
        apiResponse: MutableLiveData<ApiResponse<RestaurantResponse>>,
        resId: String
    ) {
        object : DataFetchCall<RestaurantResponse>(apiResponse) {
            override fun createCallAsync(): Deferred<Response<RestaurantResponse>> {
                return apiServices.getRestaurantDetails(resId)
            }

            override fun shouldFetchFromDB(): Boolean {
                return false
            }
        }.execute()
    }




}