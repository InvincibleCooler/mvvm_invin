package invin.mvvm_invin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import invin.mvvm_invin.db.ApiEntity
import invin.mvvm_invin.repository.book.DetailRepository
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: DetailRepository) : BaseViewModel() {
    companion object {
        private const val TAG = "DetailViewModel"
    }

    suspend fun insertData() {
        repository.insertBook()
    }

    fun getTotalCount(): LiveData<Int> {
        return repository.getTotalCount().asLiveData()
    }

    suspend fun deleteAllData(): Int {
        return repository.deleteAll()
    }

    val allData: LiveData<List<ApiEntity>> = repository.getAll().asLiveData()
}