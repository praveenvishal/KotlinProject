package com.example.kotlinproject.viewModel.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
/**
 * Created by Deepak Sharma on 01/07/19.
 */
class HomeViewModel : ViewModel() {
    var mIsPermissionGranted: MutableLiveData<Boolean> = MutableLiveData()
}