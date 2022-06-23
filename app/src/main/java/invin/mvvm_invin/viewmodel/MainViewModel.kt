package invin.mvvm_invin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import invin.mvvm_invin.net.Resource
import invin.mvvm_invin.net.res.SearchRes
import invin.mvvm_invin.repository.book.BookRepository
import javax.inject.Inject

//@HiltViewModel
class MainViewModel (private val bookRepository: BookRepository) : BaseViewModel() {
    companion object {
        private const val TAG = "MainViewModel"
    }

    private val _resource = MutableLiveData<Resource<SearchRes>>().apply {
        postValue(Resource.success(null))
    }
    val resource: LiveData<Resource<SearchRes>> get() = _resource

    suspend fun getBookList(query: String, page: String = "1") {
        _showProgress.postValue(true)
        val resource = bookRepository.getBookList(query = query, page = page)
        _showProgress.postValue(false)
        _resource.postValue(resource)
        lastRequestedTime = System.currentTimeMillis()
    }

    class Factory(private val repository: BookRepository) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainViewModel(repository) as T
        }
    }
}