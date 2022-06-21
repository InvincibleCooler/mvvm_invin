package invin.mvvm_invin.repository.book

import invin.mvvm_invin.net.Resource
import invin.mvvm_invin.net.res.SearchRes


class BookRepository(
    private val localDataSource: BookLocalDataSource,
    private val remoteDataSource: BookRemoteDataSource
) {
    companion object {
        private const val TAG = "BookRepository"
    }

    suspend fun getBookList(key: String = "", query: String = "", page: String = "1"): Resource<SearchRes> {
        return if (query.isEmpty()) {
            localDataSource.getBookList(key)
        } else {
            remoteDataSource.getBookList(query, page)
        }
    }
}