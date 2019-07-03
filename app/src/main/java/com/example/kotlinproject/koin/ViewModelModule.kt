package com.example.kotlinproject.koin


import com.example.kotlinproject.global.common.AppApplication
import com.example.kotlinproject.viewModel.list.NewsViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module



val viewModelModule = module {
//    viewModel { MainViewModel(androidApplication() as AppApplication, get()) }
    viewModel { NewsViewModel(androidApplication() as AppApplication, get()) }

}