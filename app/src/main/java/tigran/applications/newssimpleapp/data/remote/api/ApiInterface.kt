package tigran.applications.newssimpleapp.data.remote.api

import retrofit2.http.GET
import retrofit2.http.Query
import tigran.applications.newssimpleapp.data.remote.data.Response

interface ApiInterface {
    @GET("search")
    suspend fun getNews(@Query("api-key") apiKey: String): Response
}