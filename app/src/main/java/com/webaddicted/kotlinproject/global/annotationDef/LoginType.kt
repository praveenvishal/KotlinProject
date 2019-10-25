package com.webaddicted.kotlinproject.global.annotationDef

import androidx.annotation.IntDef

class LoginType {
    @IntDef(GOOGLE, FACEBOOK)
    @Retention(AnnotationRetention.SOURCE)
    annotation class SocialLoginType

    companion object {
        const val GOOGLE = 800
        const val FACEBOOK = 801
    }
}