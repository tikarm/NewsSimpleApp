package tigran.applications.newssimpleapp.data.repository

import tigran.applications.newssimpleapp.data.local.datasource.LocalDataSource
import tigran.applications.newssimpleapp.data.local.entity.NewsEntity
import tigran.applications.newssimpleapp.data.remote.data.NewsDto
import tigran.applications.newssimpleapp.data.remote.datasource.RemoteDataSource
import tigran.applications.newssimpleapp.data.remote.exception.ApiException
import tigran.applications.newssimpleapp.domain.model.NewsModel
import tigran.applications.newssimpleapp.domain.repository.NewsRepository

class GetNewsRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : NewsRepository {

    @Throws(ApiException::class)
    override suspend fun getNews(): List<NewsModel> {
        val newsEntityList = remoteDataSource.getNews().map { it.toNewsEntity() }
        localDataSource.insertNews(newsEntityList)

        return localDataSource.getNews()?.map { it.toNewsModel() } ?: emptyList()
    }

    private fun NewsDto.toNewsEntity(): NewsEntity {
        return NewsEntity(
            id = id,
            sectionName = sectionName,
            webTitle = webTitle
        )
    }

    private fun NewsEntity.toNewsModel(): NewsModel {
        return NewsModel(
            id = id,
            sectionName = sectionName,
            webTitle = webTitle
        )
    }
}