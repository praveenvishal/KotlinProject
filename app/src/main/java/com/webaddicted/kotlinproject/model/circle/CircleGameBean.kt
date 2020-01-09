package com.webaddicted.kotlinproject.model.circle

open class CircleGameBean : Comparator<CircleGameBean> {
    var id:String = ""
    var gameName:String = ""
    var gameImg:String = ""
  override fun compare(m1: CircleGameBean?, m2: CircleGameBean?): Int {
        return m1?.gameName?.compareTo(m2?.gameName.toString())!!
    }
//    constructor() : this()
//    constructor() : this()

}
