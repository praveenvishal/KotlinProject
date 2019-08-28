package com.example.kotlinproject.viewModel.map

import android.location.Location
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinproject.model.repository.news.NewsRepository
import org.koin.core.KoinComponent

/**
 * Created by Deepak Sharma on 01/07/19.
 */
class MapViewModel( private val projectRepository: NewsRepository) :ViewModel(), KoinComponent {
//  gteUpdated location
    var locationUpdated = MutableLiveData<Location>();

}
