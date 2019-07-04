package com.prodege.shopathome.model.networkCall

import com.example.kotlinproject.model.respo.newsChannel.NewsChanelRespo
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiServices {
    @GET
    fun getChannelListName(@Url strUrl: String): Deferred<Response<NewsChanelRespo>>//Call<CategoryDetails>
}