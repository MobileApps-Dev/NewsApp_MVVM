package com.mvvm.newapp.ui.newslist.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mvvm.newapp.R
import com.mvvm.newapp.data.api.ApiHelperImpl
import com.mvvm.newapp.data.api.RetrofitBuilder
import com.mvvm.newapp.data.model.News
import com.mvvm.newapp.ui.base.ViewModelFactory
import com.mvvm.newapp.ui.newslist.adapter.NewsListAdapter
import com.mvvm.newapp.ui.newslist.viewmodel.NewsListViewModel
import com.mvvm.newapp.ui.newslist.viewmodel.ParallNewsListViewModel
import com.mvvm.newapp.ui.newslist.viewmodel.SeriesNewsListViewModel
import com.mvvm.newapp.utils.Status
import kotlinx.android.synthetic.main.activity_news_list.*

class NewsListActivity : AppCompatActivity() {

    private lateinit var newsListAdapter: NewsListAdapter
    private lateinit var newsListViewModel: NewsListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_list)

        setupUI()
        setupViewModel()
        setupObserver()
    }

    private fun setupUI() {

        recyclerView.layoutManager = LinearLayoutManager(this)
        newsListAdapter = NewsListAdapter(arrayListOf())

        recyclerView.addItemDecoration(

            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = newsListAdapter
    }

    private fun setupObserver() {

        newsListViewModel.getNews().observe(this, Observer {

            when (it.status) {
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    it.data?.let { newsList->
                        renderList(newsList)
                    }
                    recyclerView.visibility=View.VISIBLE
                }
                Status.ERROR -> {
                    progressBar.visibility = View.GONE
                    Toast.makeText(this,it.message,Toast.LENGTH_SHORT).show()
                }

                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    recyclerView.visibility=View.GONE
                }
            }
        })

    }

    private fun renderList(newsList: List<News>) {
        newsListAdapter.addData(newsList)
        newsListAdapter.notifyDataSetChanged()
    }

    private fun setupViewModel() {
        newsListViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelperImpl(RetrofitBuilder.apiService))
        ).get(NewsListViewModel::class.java)
    }
}