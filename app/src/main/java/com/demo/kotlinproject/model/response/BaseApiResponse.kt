package com.demo.kotlinproject.model.response

import com.google.gson.annotations.SerializedName

class BaseApiResponse<T> : java.io.Serializable {
    @SerializedName("statusCode")
    var status: Int = -1
    @SerializedName("APICODERESULT")
    var apiCodeResult: String = ""
    @SerializedName("result")
    var result: T? = null



}