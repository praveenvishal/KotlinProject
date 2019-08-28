package com.example.kotlinproject.global.koin


import com.example.kotlinproject.global.common.AppApplication
import com.example.kotlinproject.viewModel.home.HomeViewModel
import com.example.kotlinproject.viewModel.list.NewsViewModel
import com.example.kotlinproject.viewModel.map.MapViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Deepak Sharma on 01/07/19.
 */
val viewModelModule = module {

    viewModel { HomeViewModel(get()) }
    /**
     * if we need application class context then we use newsViewModel
     */
    viewModel { NewsViewModel(androidApplication() as AppApplication, get()) }
    viewModel { MapViewModel(get()) }
}