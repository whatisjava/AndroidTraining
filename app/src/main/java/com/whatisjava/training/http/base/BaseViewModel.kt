package com.whatisjava.training.http.base

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonSyntaxException
import com.whatisjava.training.http.SingleLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import java.net.UnknownHostException
import java.net.UnknownServiceException

open class BaseViewModel : ViewModel() {

    //加载中
    val loadingLiveData = SingleLiveData<Boolean>()

    //异常
    val errorLiveData = SingleLiveData<BaseException>()

    fun launch(
        block: suspend () -> Unit,
        error: suspend (BaseException) -> Unit
    ) {
        loadingLiveData.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                block()
            } catch (e: Exception) {
                Log.d("exception--->", e.message ?: e.toString())
                val errorCode = when (e) {
                    is HttpException -> {
                        e.code()
                    }
                    is IOException -> {
                        when (e) {
                            is UnknownHostException -> {
                                1001
                            }
                            is UnknownServiceException -> {
                                1002
                            }
                            else -> {
                                1000
                            }                            }
                    }
                    is JsonSyntaxException -> {
                        2000
                    }
                    else -> {
                        -1
                    }
                }
                error(BaseException(errorCode, e.message))
            } finally {
                loadingLiveData.postValue(false)
            }
        }
    }
}