package com.whatisjava.training.http.repository

import com.whatisjava.training.http.RespState
import com.whatisjava.training.http.RetrofitManager
import com.whatisjava.training.http.api.ApiService
import com.whatisjava.training.http.base.BaseRepository
import com.whatisjava.training.http.model.Msg

class DemoRepository : BaseRepository() {

    private var apiService: ApiService = RetrofitManager.initRetrofit().getService(ApiService::class.java)

    suspend fun sendVerifyCode(
        areaCode: String,
        mobile: String,
        type: String
    ): RespState<Msg> {
        return executeResp(apiService.sendVerifyCode(areaCode, mobile, type))
    }
}