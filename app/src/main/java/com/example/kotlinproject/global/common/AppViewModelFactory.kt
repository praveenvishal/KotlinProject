package com.prodege.sbshop.model.repo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinproject.viewModel.main.MainViewModel

class AppViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            val key = "UserProfileViewModel"
            if (hashMapViewModel.containsKey(MainViewModel::class.java)) {
                return getViewModel(key) as T
            } else {
//               getVm(modelClass)
                hashMapViewModel.put(MainViewModel::class.java, modelClass as ViewModel)
                addViewModel(key, modelClass as T)
                return getViewModel(key) as T
            }
        } else {
            return modelClass as T;
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }


    companion object {
        val hashMapViewModel = HashMap<Class<out ViewModel>, ViewModel>()
        //        val hashMapViewModel = HashMap<String, ViewModel>()
        fun addViewModel(key: String, viewModel: ViewModel?) {
            hashMapViewModel.put(MainViewModel::class.java, viewModel!!)
        }

        fun getViewModel(key: String): ViewModel? {
            return hashMapViewModel[MainViewModel::class.java]
        }
    }
}



