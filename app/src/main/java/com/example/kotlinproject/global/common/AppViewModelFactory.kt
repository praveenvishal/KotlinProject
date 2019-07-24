package com.prodege.sbshop.model.repo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinproject.viewModel.common.CommonViewModel

class AppViewModelFactory() : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(CommonViewModel::class.java)) {
            val key = modelClass.simpleName
            if (hashMapViewModel.containsKey(key)) {
                getViewModel(key) as T
            } else {
                val viewModels = super.create(modelClass)
                addViewModel(key, viewModels as ViewModel)
                getViewModel(key) as T
            }
        } else {
            super.create(modelClass)
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

    companion object {
        val hashMapViewModel = HashMap<String, ViewModel>()
        fun addViewModel(key: String, viewModel: ViewModel) {
            hashMapViewModel.put(key, viewModel)
        }

        fun getViewModel(key: String): ViewModel? {
            return hashMapViewModel[key]
        }

        open fun clearCommonVm() {
            hashMapViewModel.clear()
        }
    }
}