package com.mvvm.newapp.data.repository

import com.mvvm.newapp.data.api.ApiHelper
import com.mvvm.newapp.data.model.News

class NewsListRepositoryImpl(private val apiHelper: ApiHelper) : NewsListRepository {
    override suspend fun getNews() = apiHelper.getNews()

    override suspend fun getMoreNews() = apiHelper.getMoreNews()
}