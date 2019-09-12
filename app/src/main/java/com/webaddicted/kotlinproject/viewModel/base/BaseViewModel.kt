package com.webaddicted.kotlinproject.viewModel.base

import androidx.lifecycle.ViewModel
import com.webaddicted.kotlinproject.global.sharedPref.PreferenceMgr
import org.koin.core.KoinComponent
import org.koin.core.inject

/**
 * Created by Deepak Sharma on 01/07/19.
 */
open class BaseViewModel() :ViewModel(), KoinComponent {
    protected val preferenceMgr: PreferenceMgr by inject()
}