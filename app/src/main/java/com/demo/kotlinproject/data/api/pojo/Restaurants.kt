package com.demo.kotlinproject.data.api.pojo

import androidx.databinding.BaseObservable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Restaurants(@SerializedName("restaurant")
                       @Expose
                       val restaurant: Restaurant? = null): BaseObservable() {




}