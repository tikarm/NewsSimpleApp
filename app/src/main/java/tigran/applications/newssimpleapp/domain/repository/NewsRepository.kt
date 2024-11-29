package tigran.applications.newssimpleapp.domain.repository

import tigran.applications.newssimpleapp.domain.model.NewsModel

interface NewsRepository {
    suspend fun getNews(): List<NewsModel>
}