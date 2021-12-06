package com.whatisjava.training.http.model

data class ApiResp<T>(
    val code: Int? = null,
    val msg: String? = null,
    val data: T? = null,
)