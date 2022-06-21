package invin.mvvm_invin.repository.book

import com.google.gson.Gson
import invin.mvvm_invin.db.ApiDao
import invin.mvvm_invin.net.Resource
import invin.mvvm_invin.net.res.SearchRes
import invin.mvvm_invin.repository.BaseLocalDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class BookLocalDataSource(private val dao: ApiDao) : BaseLocalDataSource() {
    companion object {
        private const val TAG = "BookLocalDataSource"
    }

    suspend fun getBookList(key: String): Resource<SearchRes> {
        return withContext(Dispatchers.IO) {
            try {
                val body = dao.getApi(key)
                Resource.success(Gson().fromJson(body, SearchRes::class.java))
            } catch (exception: Exception) {
                Resource.error(exception)
            }
        }
    }
}