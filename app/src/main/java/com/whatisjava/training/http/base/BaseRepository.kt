package com.whatisjava.training.http.base

import com.whatisjava.training.http.ResState
import com.whatisjava.training.http.model.ApiResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import java.io.IOException

open class BaseRepository {

    suspend fun <T : Any> executeResp(
        resp: ApiResponse<T>, successBlock: (suspend CoroutineScope.() -> Unit)? = null,
        errorBlock: (suspend CoroutineScope.() -> Unit)? = null
    ): ResState<T> {
        return coroutineScope {
            if (resp.code == 200) {
                successBlock?.let { it }
                ResState.Success(resp.data)
            } else {
                errorBlock?.let { it }
                ResState.Error(IOException(resp.msg))
            }
        }
    }
}
