package com.whatisjava.training.http

import com.whatisjava.training.http.base.BaseException


sealed class RespState<out T: Any> {
    data class Success<out T: Any>(val data: T?): RespState<T>()
    data class Error(val exception: BaseException): RespState<Nothing>()
}
