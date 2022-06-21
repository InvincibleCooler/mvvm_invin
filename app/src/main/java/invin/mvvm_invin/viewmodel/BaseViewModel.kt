package invin.mvvm_invin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import invin.mvvm_invin.constants.TimeConstants


abstract class BaseViewModel : ViewModel() {
    open val expiredTimeLimit = TimeConstants.EXPIRED_TIME_LIMIT

    protected val _showProgress = MutableLiveData<Boolean>().apply {
        postValue(false)
    }
    val showProgress: LiveData<Boolean> get() = _showProgress
}