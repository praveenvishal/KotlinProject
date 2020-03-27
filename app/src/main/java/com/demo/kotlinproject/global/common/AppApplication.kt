package com.demo.kotlinproject.global.common

import android.app.Application
import android.content.Context
import android.content.IntentFilter
import android.net.ConnectivityManager
import com.facebook.stetho.Stetho
import com.demo.kotlinproject.global.koin.appModule
import com.demo.kotlinproject.global.koin.commonModelModule
import com.demo.kotlinproject.global.koin.repoModule
import com.demo.kotlinproject.global.koin.viewModelModule
import com.demo.kotlinproject.global.sharedpref.PreferenceUtils
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.module.Module


class AppApplication : Application() {
    private val mNetworkReceiver = NetworkChangeReceiver()

    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        Stetho.initializeWithDefaults(this)
        PreferenceUtils.Companion.getInstance(this)
        startKoin {
            androidLogger()
            androidContext(this@AppApplication)
            modules(getModule())
        }
        checkInternetConnection()
    }


    private fun getModule(): List<Module> {
        return listOf(
            appModule,
            viewModelModule,
            repoModule,
            commonModelModule
        )
    }

    private fun checkInternetConnection() {
        registerReceiver(mNetworkReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }
}
