package com.mvvm.newapp.data.api

import com.mvvm.newapp.data.model.News

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

    override suspend fun getNews() = apiService.getNews()
    override suspend fun getMoreNews() = apiService.getMoreNews()
}