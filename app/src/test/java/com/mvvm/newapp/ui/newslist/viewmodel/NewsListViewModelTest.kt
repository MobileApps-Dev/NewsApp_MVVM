package com.mvvm.newapp.ui.newslist.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.mvvm.newapp.data.model.News
import com.mvvm.newapp.data.repository.NewsListRepository
import com.mvvm.newapp.ui.newslist.viewmodel.utils.TestCoroutineRule
import com.mvvm.newapp.utils.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner
import java.lang.RuntimeException
import java.util.*

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class NewsListViewModelTest {

    @get:Rule
    val testInstantExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCorotineRule = TestCoroutineRule()

    @Mock
    private lateinit var newsListRepository: NewsListRepository

    @Mock
    private lateinit var newsListObserver: Observer<Resource<List<News>>>

    @Before
    fun setup() {

    }

    @Test
    fun givenServerResponse200_whenFetch_shouldReturnSuccess() {
        testCorotineRule.runBlockingTest {

            doReturn(emptyList<News>())
                .`when`(newsListRepository)
                .getNews()

            val viewModel = NewsListViewModel(newsListRepository)
            viewModel.getNews().observeForever(newsListObserver)
            verify(newsListRepository).getNews()
            verify(newsListObserver).onChanged(Resource.success(emptyList()))
            viewModel.getNews().removeObserver(newsListObserver)
        }
    }

    @Test
    fun givenServerResponseError_whenFetch_shouldReturnError() {
        testCorotineRule.runBlockingTest {
            val errorMessage="Error"
            doThrow(RuntimeException(errorMessage))
                .`when`(newsListRepository)
                .getNews()

            val viewModel = NewsListViewModel(newsListRepository)
            viewModel.getNews().observeForever(newsListObserver)
            verify(newsListRepository).getNews()
            verify(newsListObserver).onChanged(Resource.error(RuntimeException(errorMessage).toString(),null))
            viewModel.getNews().removeObserver(newsListObserver)
        }
    }

    @After
    fun tearDown() {

    }
}