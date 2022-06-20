package invin.mvvm_invin.viewmodel

import androidx.lifecycle.ViewModel
import invin.mvvm_invin.constants.TimeConstants


abstract class BaseViewModel : ViewModel() {
    open val expiredTimeLimit = TimeConstants.EXPIRED_TIME_LIMIT
}