package com.example.kotlinproject.model.preference

data class PreferenceBean(
    var name: String,
    var gender: String? = null,
    var age: Int = 0,
    var weight: Long = 0,
    var isMarried: Boolean = false
) {
//    constructor() : this()
//    constructor() : this()

}