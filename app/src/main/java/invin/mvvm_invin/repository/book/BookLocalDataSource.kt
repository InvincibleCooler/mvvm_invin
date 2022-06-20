package invin.mvvm_invin.repository.book

import invin.mvvm_invin.db.ApiDao
import invin.mvvm_invin.net.Resource
import invin.mvvm_invin.repository.BaseLocalDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class BookLocalDataSource(private val dao: ApiDao) : BaseLocalDataSource() {
    suspend fun getBookList(key: String) {
        return withContext(Dispatchers.IO) {
            try {
                val res = dao.getApi(key)
                Resource.success(res)
            } catch (exception: Exception) {
                Resource.error(exception)
            }
        }
    }
}