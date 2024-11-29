package tigran.applications.newssimpleapp.data.remote.repository

import tigran.applications.newssimpleapp.data.remote.api.ApiClient
import tigran.applications.newssimpleapp.data.remote.data.NewsDto
import tigran.applications.newssimpleapp.data.remote.datasource.RemoteDataSource
import tigran.applications.newssimpleapp.domain.model.NewsModel
import tigran.applications.newssimpleapp.domain.repository.NewsRepository

class GetNewsFromRemoteRepository : NewsRepository {
    private val remoteDataSource = RemoteDataSource(ApiClient.api)

    override suspend fun getNews(): List<NewsModel> {
        return remoteDataSource.getNews().map { it.toNewsModel() }
    }

    private fun NewsDto.toNewsModel(): NewsModel {
        return NewsModel(
            id = id,
            sectionID = sectionID,
            sectionName = sectionName,
            webPublicationDate = webPublicationDate,
            webTitle = webTitle
        )
    }
}