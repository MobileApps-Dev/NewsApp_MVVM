package com.mvvm.newapp.data.api

import com.mvvm.newapp.data.model.News

interface ApiHelper {

    suspend fun getNews():List<News>
    suspend fun getMoreNews():List<News>
}