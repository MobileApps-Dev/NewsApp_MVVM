package com.mvvm.newapp.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mvvm.newapp.data.api.ApiHelper
import com.mvvm.newapp.data.repository.NewsListRepository
import com.mvvm.newapp.data.repository.NewsListRepositoryImpl
import com.mvvm.newapp.ui.newslist.viewmodel.NewsListViewModel
import com.mvvm.newapp.ui.newslist.viewmodel.ParallNewsListViewModel
import com.mvvm.newapp.ui.newslist.viewmodel.SeriesNewsListViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(NewsListViewModel::class.java)) {
            return NewsListViewModel(NewsListRepositoryImpl(apiHelper)) as T
        }

        if (modelClass.isAssignableFrom(SeriesNewsListViewModel::class.java)) {
            return SeriesNewsListViewModel(NewsListRepositoryImpl(apiHelper)) as T
        }
        if (modelClass.isAssignableFrom(ParallNewsListViewModel::class.java)) {
            return ParallNewsListViewModel(NewsListRepositoryImpl(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown Class Name ")
    }
}