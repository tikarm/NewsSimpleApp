package tigran.applications.newssimpleapp.data.local.datasource

import tigran.applications.newssimpleapp.data.local.dao.NewsDao
import tigran.applications.newssimpleapp.data.local.entity.NewsEntity


class LocalDataSource(
    private val newsDao: NewsDao
) {
    suspend fun getNews() = newsDao.getAll()

    suspend fun insertNews(news: List<NewsEntity>) = newsDao.insertAll(news)
}