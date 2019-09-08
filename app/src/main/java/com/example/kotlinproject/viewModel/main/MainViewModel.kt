package com.example.kotlinproject.viewModel.main

import com.example.kotlinproject.model.bean.language.LanguageBean
import com.example.kotlinproject.model.repository.news.NewsRepository
import com.example.kotlinproject.viewModel.base.BaseViewModel

/**
 * Created by Deepak Sharma on 01/07/19.
 */
class MainViewModel(private val projectRepository: NewsRepository) :BaseViewModel() {
    fun setLanguage(get: LanguageBean) {
        preferenceMgr.setLanguage(get)
    }
}