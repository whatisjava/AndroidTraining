package com.whatisjava.training.http.model

data class ApiResponse<T>(
    var code: Int? = null,
    var msg: String? = null,
    var data: T? = null,
    var action: Action? = null,
)

enum class ApiState {
    LOADING, SUCCESS, ERROR, EMPTY
}
