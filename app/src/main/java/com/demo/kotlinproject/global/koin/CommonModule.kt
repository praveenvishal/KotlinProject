package com.demo.kotlinproject.global.koin

import com.demo.kotlinproject.global.sharedpref.PreferenceUtils
import org.koin.dsl.module

val commonModelModule = module {
    single { PreferenceUtils() }
}