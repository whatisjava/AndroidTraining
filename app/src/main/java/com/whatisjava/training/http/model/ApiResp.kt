package com.whatisjava.training.http.model

data class ApiResp<T>(
    var code: Int? = null,
    var msg: String? = null,
    var data: T? = null,
    var action: Action? = null,
)