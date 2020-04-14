package com.demo.kotlinproject.di.modules

import com.demo.kotlinproject.data.preference.PreferenceUtils
import org.koin.dsl.module

val commonModelModule = module {
    single { PreferenceUtils() }
}