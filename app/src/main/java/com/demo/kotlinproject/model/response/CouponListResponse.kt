package com.demo.kotlinproject.model.response

import com.google.gson.annotations.SerializedName

class CouponListResponse{
    @SerializedName("description_title")
    var description_title: String = ""

    @SerializedName("decription_image")
    var decription_image: String = ""

    @SerializedName("description_body")
    var description_body: String = ""

    @SerializedName("latitudes")
    var latitudes: String = ""


    @SerializedName("longitude")
    var longitude: String = ""


    @SerializedName("cupons")
    var cupons = ArrayList<Coupon>()

    @SerializedName("banner")
    var banners = ArrayList<String>()

    open class Coupon{
        @SerializedName("title")
        var title: String = ""
        @SerializedName("description")
        var description: String = ""
        @SerializedName("price")
        var price: String = ""

    }


}