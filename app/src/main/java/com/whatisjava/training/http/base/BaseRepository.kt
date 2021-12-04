package com.whatisjava.training.http.base

import com.whatisjava.training.http.RespState
import com.whatisjava.training.http.model.ApiResp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import java.io.IOException

open class BaseRepository {

    suspend fun <T : Any> executeResp(
        resp: ApiResp<T>, successBlock: (suspend CoroutineScope.() -> Unit)? = null,
        errorBlock: (suspend CoroutineScope.() -> Unit)? = null
    ): RespState<T> {
        return coroutineScope {
            when (resp.code) {
                200 -> {
                    successBlock?.let { it }
                    RespState.Success(resp.data)
                }
                301 -> {
                    successBlock?.let { it }
                    RespState.Success(resp.data)
                }
                else -> {
                    errorBlock?.let { it }
                    RespState.Error(BaseException(resp.code, resp.msg))
                }
            }
        }
    }
}
