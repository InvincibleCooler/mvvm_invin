package invin.mvvm_invin.net

import invin.mvvm_invin.net.res.SearchRes
import retrofit2.http.GET
import retrofit2.http.Path


interface ServiceApi {
    // eg) https://api.itbook.store/1.0/search/kotlin/1
    @GET("search/{query}/{page}")
    suspend fun getSearch(@Path("query") query: String, @Path("page") page: String): SearchRes
}