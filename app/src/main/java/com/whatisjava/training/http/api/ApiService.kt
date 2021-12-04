package com.whatisjava.training.http.api

import com.whatisjava.training.http.model.ApiResp
import com.whatisjava.training.http.model.Msg
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(ApiPathName.SEND_VERIFY_CODE)
    suspend fun sendVerifyCode(
        @Query("areaCode") areaCode: String,
        @Query("phone") mobile: String,
        @Query("type") type: String
    ): ApiResp<Msg>

}