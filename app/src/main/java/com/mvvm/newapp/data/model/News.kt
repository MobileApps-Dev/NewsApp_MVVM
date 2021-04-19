package com.mvvm.newapp.data.model

import com.google.gson.annotations.SerializedName

data class News(
        @SerializedName("title")
        val title: String = "",

        @SerializedName("description")
        val description: String = "",

        @SerializedName("source")
        val source: String = "",

        @SerializedName("url")
        val url: String = "",

        @SerializedName("imageUrl")
        val imageUrl: String = ""

)
