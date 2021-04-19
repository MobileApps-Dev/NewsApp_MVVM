package com.mvvm.newapp.ui.newslist.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mvvm.newapp.R
import com.mvvm.newapp.data.model.News
import kotlinx.android.synthetic.main.item_layout.view.*

class NewsListAdapter(private var newsList: ArrayList<News>) :
    RecyclerView.Adapter<NewsListAdapter.DataViewModel>() {

    class DataViewModel(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(news: News) {

            itemView.textViewTitle.text = news.title
            itemView.textViewDescription.text = news.description
            itemView.textViewSource.text = news.source

            Glide.with(itemView.imageViewBanner.context)
                .load(news.imageUrl)
                .into(itemView.imageViewBanner)
            itemView.setOnClickListener {

                var builder=CustomTabsIntent.Builder()
                val customTabIntent=builder.build()
                customTabIntent.launchUrl(it.context, Uri.parse(news.url))
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewModel {
        return DataViewModel(
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: DataViewModel, position: Int) {
        holder.bind(newsList[position])
    }

    override fun getItemCount(): Int = newsList.size

    fun addData(list: List<News>) {
        newsList.addAll(list)
    }
}