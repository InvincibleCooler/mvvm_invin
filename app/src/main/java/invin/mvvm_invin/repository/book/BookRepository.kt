package invin.mvvm_invin.repository.book


class BookRepository(
    private val localDataSource: BookLocalDataSource,
    private val remoteDataSource: BookRemoteDataSource
) {
    suspend fun getBookList(key: String, query: String, page: String) {
        if (true || true /*in 5 minutes*/) {
            localDataSource.getBookList(key)
        } else {
            remoteDataSource.getBookList(query, page)
        }
    }
}