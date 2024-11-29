package tigran.applications.newssimpleapp.data.repository

import tigran.applications.newssimpleapp.data.local.datasource.LocalDataSource
import tigran.applications.newssimpleapp.data.local.db.DatabaseInstance
import tigran.applications.newssimpleapp.data.local.entity.NewsEntity
import tigran.applications.newssimpleapp.data.remote.api.ApiClient
import tigran.applications.newssimpleapp.data.remote.data.NewsDto
import tigran.applications.newssimpleapp.data.remote.datasource.RemoteDataSource
import tigran.applications.newssimpleapp.domain.model.NewsModel
import tigran.applications.newssimpleapp.domain.repository.NewsRepository

class GetNewsRepository : NewsRepository {
    private val remoteDataSource = RemoteDataSource(ApiClient.api)
    private val localDataSource = LocalDataSource(DatabaseInstance.getInstance().newsDao())

    override suspend fun getNews(): List<NewsModel> {
        val newsEntityList = remoteDataSource.getNews().map { it.toNewsEntity() }
        localDataSource.insertNews(newsEntityList)

        return localDataSource.getNews()?.map { it.toNewsModel() } ?: emptyList()
    }

    private fun NewsDto.toNewsEntity(): NewsEntity {
        return NewsEntity(
            id = id,
            sectionID = sectionID,
            sectionName = sectionName,
            webPublicationDate = webPublicationDate,
            webTitle = webTitle
        )
    }

    private fun NewsEntity.toNewsModel(): NewsModel {
        return NewsModel(
            id = id,
            sectionID = sectionID,
            sectionName = sectionName,
            webPublicationDate = webPublicationDate,
            webTitle = webTitle
        )
    }
}