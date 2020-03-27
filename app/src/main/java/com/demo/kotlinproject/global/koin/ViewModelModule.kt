package com.demo.kotlinproject.global.koin


import com.demo.kotlinproject.viewModel.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
}