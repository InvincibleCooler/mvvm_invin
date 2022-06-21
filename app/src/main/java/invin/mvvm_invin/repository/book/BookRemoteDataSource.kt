package invin.mvvm_invin.repository.book

import invin.mvvm_invin.net.Resource
import invin.mvvm_invin.net.ServiceApi
import invin.mvvm_invin.net.res.SearchRes
import invin.mvvm_invin.repository.BaseRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class BookRemoteDataSource(private val serviceApi: ServiceApi) : BaseRemoteDataSource() {
    companion object {
        private const val TAG = "BookRemoteDataSource"
    }

    suspend fun getBookList(query: String, page: String): Resource<SearchRes> {
        return withContext(Dispatchers.IO) {
            try {
                val res = serviceApi.getSearch(query, page)
                Resource.success(res)
            } catch (exception: Exception) {
                Resource.error(exception)
            }
        }
    }
}