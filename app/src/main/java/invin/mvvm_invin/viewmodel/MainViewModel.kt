package invin.mvvm_invin.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import invin.mvvm_invin.net.Resource
import invin.mvvm_invin.net.res.SearchRes
import invin.mvvm_invin.repository.book.BookRepository


class MainViewModel(private val bookRepository: BookRepository) : BaseViewModel() {
    companion object {
        private const val TAG = "MainViewModel"
    }

    private val _resource = MutableLiveData<Resource<SearchRes>>().apply {
        postValue(Resource.success(null))
    }
    val resouce: LiveData<Resource<SearchRes>> get() = _resource

    fun getBookList() {
        Log.d(TAG, "getBookList()")
    }

    class Factory(private val repository: BookRepository) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainViewModel(repository) as T
        }
    }
}