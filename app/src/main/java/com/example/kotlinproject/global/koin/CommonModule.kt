package com.example.kotlinproject.global.koin

import com.example.kotlinproject.global.common.ImagePicker
import com.example.kotlinproject.global.sharedPref.PreferenceMgr
import com.example.kotlinproject.global.sharedPref.PreferenceUtils
import org.koin.dsl.module

/**
 * Created by Deepak Sharma on 01/07/19.
 */
val commonModelModule = module {
    single { PreferenceUtils() }
    single { PreferenceMgr(get()) }
    single { ImagePicker() }

//    single { AppViewModelFactory() }
//    single { ThemeColors(get(), get()) }
}