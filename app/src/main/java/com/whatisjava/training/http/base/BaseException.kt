package com.whatisjava.training.http.base

class BaseException(
    val errorCode: Int? = null,
    val errorMsg: String? = null,
    var pathName: String? = null,
) : Exception() {
    override fun toString() = "$errorCode : $errorMsg : $pathName"
}