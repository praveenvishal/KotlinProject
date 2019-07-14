package com.example.kotlinproject.global.koin

import com.example.kotlinproject.model.repository.news.NewsRepository
import org.koin.dsl.module
/**
 * Created by Deepak Sharma on 01/07/19.
 */
val repoModule = module {

    single { NewsRepository(get()) }

}