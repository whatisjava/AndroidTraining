package com.whatisjava.training.http.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.whatisjava.training.http.SingleLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {

    //加载中
    val loadingLiveData = SingleLiveData<Boolean>()

    val errorLiveData = SingleLiveData<Throwable>()

    fun launch(
        block: suspend () -> Unit,
        error: suspend (Throwable) -> Unit,
        complete: suspend () -> Unit
    ) {
        loadingLiveData.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                block()
            } catch (e: Exception) {
                error(e)
            } finally {
                complete()
            }
        }
    }
}