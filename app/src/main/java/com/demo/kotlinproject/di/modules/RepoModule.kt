package com.demo.kotlinproject.di.modules

import com.demo.kotlinproject.data.repository.news.HomeRepository
import org.koin.dsl.module

val repoModule = module {

    single { HomeRepository(get()) }

}