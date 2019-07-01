package com.prodege.shopathome.model.networkCall

import com.example.kotlinproject.model.github.Project
import retrofit2.Call
import retrofit2.http.*

interface ApiServices {
    @GET("users/{user}/repos")
    fun getProjectList(@Path("user") user: String): Call<List<Project>>

    @GET("/repos/{user}/{reponame}")
    fun getProjectDetails(@Path("user") user: String, @Path("reponame") projectName: String): Call<Project>

}