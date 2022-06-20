package invin.mvvm_invin.repository.book

import invin.mvvm_invin.net.Resource
import invin.mvvm_invin.net.ServiceApi
import invin.mvvm_invin.repository.BaseRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class BookRemoteDataSource(private val serviceApi: ServiceApi) : BaseRemoteDataSource() {
    suspend fun getBookList(query: String, page: String) {
        return withContext(Dispatchers.IO) {
            try {
                val res = serviceApi.getSearch(query)
                Resource.success(res)
            } catch (exception: Exception) {
                Resource.error(exception)
            }
        }
    }
}