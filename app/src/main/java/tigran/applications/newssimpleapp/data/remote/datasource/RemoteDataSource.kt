package tigran.applications.newssimpleapp.data.remote.datasource

import tigran.applications.newssimpleapp.data.remote.api.ApiInterface
import tigran.applications.newssimpleapp.data.remote.data.NewsDto
import tigran.applications.newssimpleapp.data.remote.exception.ApiException

private const val API_KEY = "649f61a3-e5bd-4044-8021-d3edb05b0b56"

class RemoteDataSource(
    private val apiInterface: ApiInterface
) {
    @Throws(ApiException::class)
    suspend fun getNews(): List<NewsDto> {
        val response = apiInterface.getNews(API_KEY).response
        if (response?.news == null) {
            throw ApiException("Response is Empty")
        }
        return apiInterface.getNews(API_KEY).response?.news!!
    }
}