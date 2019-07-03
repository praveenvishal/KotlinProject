package com.prodege.shopathome.model.networkCall

import com.example.kotlinproject.model.github.Project
import com.example.kotlinproject.model.respo.newsChannel.NewsChanelRespo
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiServices {
    @GET("users/{user}/repos")
    fun getProjectList(@Path("user") user: String): Deferred<Response<Project>>//P: Call<List<Project>>
    @GET
    abstract fun getChannelListName(@Url strUrl: String): Deferred<Response<NewsChanelRespo>>//Call<CategoryDetails>

    @GET("/repos/{user}/{reponame}")
    fun getProjectDetails(@Path("user") user: String, @Path("reponame") projectName: String): Call<Project>

}