package invin.mvvm_invin.repository.book

import invin.mvvm_invin.db.ApiDao
import invin.mvvm_invin.db.ApiEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class DetailRepository @Inject constructor(private val dao: ApiDao) {
    companion object {
        private const val TAG = "BookDetailRepository"
    }

    suspend fun insertBook() {
        val dummyData = System.currentTimeMillis()
        dao.insertApiEntity(ApiEntity(key = dummyData, body = dummyData.toString(), dummyData))
    }

    // for test
    fun getTotalCount(): Flow<Int> {
        return dao.getTotalCount()
    }

    suspend fun deleteAll(): Int {
        return dao.deleteAll()
    }

    fun getAll(): Flow<List<ApiEntity>> {
        return dao.getAll()
    }
}