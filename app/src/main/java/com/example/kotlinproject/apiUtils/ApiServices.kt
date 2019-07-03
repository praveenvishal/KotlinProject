package com.prodege.shopathome.model.networkCall

import com.example.kotlinproject.model.respo.newsChannel.NewsChanelRespo
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiServices {
    @GET
     fun getChannelListName(@Url strUrl: String): Deferred<Response<NewsChanelRespo>>//Call<CategoryDetails>


}