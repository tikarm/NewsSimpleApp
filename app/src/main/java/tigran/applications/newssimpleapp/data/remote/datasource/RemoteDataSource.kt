package tigran.applications.newssimpleapp.data.remote.datasource

import tigran.applications.newssimpleapp.data.remote.api.ApiInterface
import tigran.applications.newssimpleapp.data.remote.data.NewsDto
import tigran.applications.newssimpleapp.data.remote.data.Response

private const val API_KEY = "649f61a3-e5bd-4044-8021-d3edb05b0b56"

class RemoteDataSource(
    private val apiInterface: ApiInterface
) {

//    @Throws(ApiException::class, UnexpectedException::class)
    suspend fun getNews(): List<NewsDto> {
        // TODO if null throw exception
        return apiInterface.getNews(API_KEY).news!!
    }
}