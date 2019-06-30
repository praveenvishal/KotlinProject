package com.example.kotlinproject.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    var mIsPermissionGranted: MutableLiveData<Boolean> = MutableLiveData()
}