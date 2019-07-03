package com.example.kotlinproject.koin

import com.example.kotlinproject.model.repository.news.NewsRepository
import org.koin.dsl.module




val repoModule = module {

    single { NewsRepository(get()) }

}