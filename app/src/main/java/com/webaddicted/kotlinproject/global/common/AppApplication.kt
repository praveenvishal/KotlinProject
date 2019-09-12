package com.webaddicted.kotlinproject.global.common

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import com.webaddicted.kotlinproject.R
import com.webaddicted.kotlinproject.global.koin.appModule
import com.webaddicted.kotlinproject.global.koin.commonModelModule
import com.webaddicted.kotlinproject.global.koin.repoModule
import com.webaddicted.kotlinproject.global.koin.viewModelModule
import com.webaddicted.kotlinproject.global.sharedPref.PreferenceUtils
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import uk.co.chrisjenx.calligraphy.CalligraphyConfig
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

/**
 * Created by Deepak Sharma on 01/07/19.
 */
class AppApplication :Application(){
    companion object {
        lateinit var context: Context

    }
    override fun onCreate() {
        super.onCreate()
        context = this
        FileUtils.createApplicationFolder()
        setupDefaultFont()
        PreferenceUtils.Companion.getInstance(this)
        startKoin {
            androidLogger()
            androidContext(this@AppApplication)
            modules(getModule())
        }
    }

    fun setupDefaultFont() {
        CalligraphyConfig.initDefault(
            CalligraphyConfig.Builder()
                .setDefaultFontPath("font/opensans_regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        )
    }
    private fun getModule(): Iterable<Module> {
        return listOf(
            appModule,
            viewModelModule,
            repoModule,
            commonModelModule
        )
    }
    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }
}