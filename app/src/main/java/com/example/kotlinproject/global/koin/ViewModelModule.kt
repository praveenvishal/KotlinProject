package com.example.kotlinproject.global.koin


import com.example.kotlinproject.global.common.AppApplication
import com.example.kotlinproject.viewModel.common.CommonViewModel
import com.example.kotlinproject.viewModel.home.HomeViewModel
import com.example.kotlinproject.viewModel.list.NewsViewModel
import com.example.kotlinproject.viewModel.main.MainViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Deepak Sharma on 01/07/19.
 */
val viewModelModule = module {
    viewModel { NewsViewModel(androidApplication() as AppApplication, get()) }
    viewModel { HomeViewModel(androidApplication() as AppApplication, get()) }
    viewModel { MainViewModel(androidApplication() as AppApplication, get()) }
}