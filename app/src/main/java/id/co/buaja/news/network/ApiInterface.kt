package id.co.buaja.news.network

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("v2/top-headlines")
    fun getNews(
        @Query("category") category: String?
    ): Deferred<Response<ApiResponse>>
}