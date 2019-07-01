package com.example.kotlinproject.global.sharedPref

import com.example.kotlinproject.global.constant.PreferenceConstant
import com.example.kotlinproject.model.preference.PreferenceBean

class PreferenceMgr {
    private var preferenceUtils: PreferenceUtils? = null
    fun setUserInfo(preferenceBean: PreferenceBean) {
        preferenceUtils?.setPreference(PreferenceConstant.PREF_USER_NAME, preferenceBean.name)
        preferenceUtils?.setPreference(PreferenceConstant.PREF_USER_AGE, preferenceBean.age)
        preferenceUtils?.setPreference(PreferenceConstant.PREF_USER_GENDER, preferenceBean.gender)
        preferenceUtils?.setPreference(PreferenceConstant.PREF_USER_WEIGHT, preferenceBean.weight)
        preferenceUtils?.setPreference(PreferenceConstant.PREF_USER_IS_MARRIED, preferenceBean.isMarried)
    }

//    fun getUserInfo(): PreferenceBean {
//
//        val preferenceBean = PreferenceBean()
////            preferenceUtils?.getPreference(PreferenceConstant.PREF_USER_NAME, ""),
////            preferenceUtils?.getPreference(PreferenceConstant.PREF_USER_AGE, 0),
////            preferenceUtils?.getPreference(PreferenceConstant.PREF_USER_WEIGHT, 0L),
////            preferenceUtils?.getPreference(PreferenceConstant.PREF_USER_IS_MARRIED, false)
////        )
//        return
//    }
}