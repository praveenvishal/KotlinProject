package com.example.kotlinproject.global.common

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import com.example.kotlinproject.apiUtils.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.module.Module

class AppApplication :Application(),Application.ActivityLifecycleCallbacks{


    companion object {
        lateinit var context: Context
        lateinit var mCurrencyActivity: Activity

    }
    override fun onCreate() {
        super.onCreate()
        context = this
        startKoin {
            androidLogger()
            androidContext(this@AppApplication)
            modules(getModule())
        }
    }
    private fun getModule(): Iterable<Module> {
        return listOf(
            appModule
        )
    }

    override fun onActivityPaused(activity: Activity) {
        mCurrencyActivity = activity
    }

    override fun onActivityResumed(activity: Activity) {
        mCurrencyActivity = activity
    }

    override fun onActivityStarted(activity: Activity?) {
    }

    override fun onActivityDestroyed(activity: Activity?) {
    }

    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
    }

    override fun onActivityStopped(activity: Activity?) {
    }

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
    }
}