package invin.mvvm_invin.repository.book

import android.util.Log
import invin.mvvm_invin.net.Resource
import invin.mvvm_invin.net.ServiceApi
import invin.mvvm_invin.net.res.SearchRes
import invin.mvvm_invin.repository.BaseRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class BookRemoteDataSource @Inject constructor(private val serviceApi: ServiceApi) : BaseRemoteDataSource() {
    companion object {
        private const val TAG = "BookRemoteDataSource"
    }

    suspend fun getBookList(query: String, page: String): Resource<SearchRes> {
        return withContext(Dispatchers.IO) {
            try {
                val res = serviceApi.getSearch(query, page)
                Log.d(TAG, "Success")
                Resource.success(res)
            } catch (exception: Exception) {
                Log.d(TAG, "Fail : ${exception.message}")
                Resource.error(exception)
            }
        }
    }
}