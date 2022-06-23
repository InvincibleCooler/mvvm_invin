package invin.mvvm_invin.repository.book

import invin.mvvm_invin.net.Resource
import invin.mvvm_invin.net.res.SearchRes


class BookRepository(
    private val remoteDataSource: BookRemoteDataSource
) {
    companion object {
        private const val TAG = "BookRepository"
    }

    suspend fun getBookList(query: String = "", page: String = "1"): Resource<SearchRes> {
        return remoteDataSource.getBookList(query, page)
    }
}