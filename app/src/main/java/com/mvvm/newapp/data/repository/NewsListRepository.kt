package com.mvvm.newapp.data.repository

import com.mvvm.newapp.data.model.News

interface NewsListRepository {

    suspend fun getNews():List<News>
    suspend fun getMoreNews():List<News>
}