package com.demo.kotlinproject.global.koin

import com.demo.kotlinproject.model.repository.news.NewsRepository
import org.koin.dsl.module

val repoModule = module {

    single { NewsRepository(get()) }

}