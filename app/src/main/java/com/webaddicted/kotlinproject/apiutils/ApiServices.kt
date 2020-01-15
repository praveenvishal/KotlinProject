package com.webaddicted.kotlinproject.apiutils

import com.webaddicted.kotlinproject.model.bean.newschannel.NewsChanelRespo
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url
/**
 * Created by Deepak Sharma on 01/07/19.
 */
interface ApiServices {
    @GET
    fun getChannelListName(@Url strUrl: String): Deferred<Response<NewsChanelRespo>>//Call<CategoryDetails>
}