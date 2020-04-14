package com.demo.kotlinproject.data.api.response

import com.demo.kotlinproject.data.api.pojo.Restaurants
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

 class SearchResponse {

    @SerializedName("results_shown")
    @Expose
    private var resultsShown: Int? = null


    @SerializedName("restaurants")
    @Expose
    val restaurants:List<Restaurants>? = null

    fun getSearchRest(): List<Restaurants>? {
        return restaurants
    }

     fun getResultsShown(): Int? {
         return resultsShown
     }



}